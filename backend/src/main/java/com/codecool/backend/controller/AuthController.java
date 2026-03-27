package com.codecool.backend.controller;

import com.codecool.backend.dto.AuthResponse;
import com.codecool.backend.dto.LoginRequest;
import com.codecool.backend.dto.RefreshTokenRequest;
import com.codecool.backend.dto.RegistrationRequest;
import com.codecool.backend.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final Optional<AuthService> authService;

    private long refreshTokenExpiration = 604800000;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegistrationRequest request, HttpServletRequest httpRequest, HttpServletResponse response){

        System.out.println("=== REGISTER ENDPOINT HIT ===");
        System.out.println("X-Forwarded-Proto = " + httpRequest.getHeader("X-Forwarded-Proto"));
        System.out.println("isSecure() = " + httpRequest.isSecure());
        System.out.println("Scheme = " + httpRequest.getScheme());
        System.out.println("Remote Addr = " + httpRequest.getRemoteAddr());

        AuthResponse authResponse = getService().register(request);

        setRefreshTokenCookie(response, authResponse.getRefreshToken());

        authResponse.setRefreshToken(null);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response) {
        AuthResponse authResponse = getService().login(request);

        // Set refresh token in httpOnly cookie
        setRefreshTokenCookie(response, authResponse.getRefreshToken());

        // Don't send refresh token in response body
        authResponse.setRefreshToken(null);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        // Extract refresh token from cookie
        String refreshToken = getRefreshTokenFromCookie(request);

        if (refreshToken == null) {
            return ResponseEntity.status(401).build();
        }

        RefreshTokenRequest refreshRequest = new RefreshTokenRequest();
        refreshRequest.setRefreshToken(refreshToken);

        AuthResponse authResponse = getService().refreshToken(refreshRequest);

        // Update refresh token cookie if rotation is implemented
        if (authResponse.getRefreshToken() != null) {
            setRefreshTokenCookie(response, authResponse.getRefreshToken());
        }

        // Don't send refresh token in response body
        authResponse.setRefreshToken(null);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getRefreshTokenFromCookie(request);

        if (refreshToken != null) {
            try {
                getService().logout(refreshToken);
            } catch (Exception e) {
                // Token already invalid — still clear the cookie
            }
        }

        // Clear the cookie regardless
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Set to TRUE in production
        cookie.setPath("/api/auth");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }


    private void setRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true); // Prevents JavaScript access (XSS protection)
        cookie.setSecure(false); // Set to TRUE in production (HTTPS only), FALSE for local development
        cookie.setPath("/api/auth"); // Only send to auth endpoints - change to /api/auth/refresh to only send RT to /refresh
        cookie.setMaxAge((int) (refreshTokenExpiration / 1000)); // Convert to seconds
        // cookie.setSameSite("Strict"); // CSRF protection (requires Spring Boot 3.0+)
        response.addCookie(cookie);
    }

    // Helper method to extract refresh token from cookie
    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private AuthService getService() {
        return authService.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Database not available"));
    }
}
