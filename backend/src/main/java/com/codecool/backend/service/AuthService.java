package com.codecool.backend.service;

import com.codecool.backend.dto.AuthResponse;
import com.codecool.backend.dto.LoginRequest;
import com.codecool.backend.dto.RefreshTokenRequest;
import com.codecool.backend.dto.RegistrationRequest;
import com.codecool.backend.model.RefreshToken;
import com.codecool.backend.security.CustomUserDetailsService;
import com.codecool.backend.security.jwt.JwtUtil;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.datasource.url")
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse register(RegistrationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of("USER"));
        user.setEnabled(true);

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String accessToken = jwtUtil.generateAccessToken(authentication);
        String refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return new AuthResponse(
                accessToken,
                refreshToken,
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> "ROLE_" + role).toArray(String[]::new)
        );
    }

    public AuthResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtil.generateAccessToken(authentication);
        String refreshToken = refreshTokenService.createRefreshToken(request.getUsername());

        return new AuthResponse(
                accessToken,
                refreshToken,
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> "ROLE_" + role).toArray(String[]::new)
        );
    }

    public AuthResponse refreshToken(RefreshTokenRequest request){
        String oldRefreshToken = request.getRefreshToken();

        // Validate against DB (checks JWT signature + not revoked + not expired)
        RefreshToken validatedToken = refreshTokenService.validateRefreshToken(oldRefreshToken);

        User user = validatedToken.getUser();

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        // Revoke all old tokens for this user (rotation)
        refreshTokenService.revokeAllForUser(user);

        // Issue fresh tokens
        String newAccessToken = jwtUtil.generateAccessToken(authentication);
        String newRefreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return new AuthResponse(
                newAccessToken,
                newRefreshToken,
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> "ROLE_" + role).toArray(String[]::new)
        );
    }

    public void logout(String refreshToken) {
        refreshTokenService.validateRefreshToken(refreshToken);
        String username = jwtUtil.getUserNameFromToken(refreshToken, true);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        refreshTokenService.revokeAllForUser(user);
    }
}
