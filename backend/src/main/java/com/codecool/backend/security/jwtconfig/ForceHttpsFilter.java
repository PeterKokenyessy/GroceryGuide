package com.codecool.backend.security.jwtconfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 10)  // After ForwardedHeaderFilter (0), before most others
public class ForceHttpsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String proto = request.getHeader("X-Forwarded-Proto");

        boolean isSecure = "https".equalsIgnoreCase(proto);

        HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
            @Override
            public boolean isSecure() {
                return isSecure || super.isSecure();
            }

            @Override
            public String getScheme() {
                return isSecure ? "https" : super.getScheme();
            }
        };

        filterChain.doFilter(wrapped, response);
    }
}