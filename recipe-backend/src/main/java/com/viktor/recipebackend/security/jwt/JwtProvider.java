package com.viktor.recipebackend.security.jwt;

import com.viktor.recipebackend.security.principal.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);
    Authentication getAuthentication(HttpServletRequest httpServletRequest);
    boolean isTokenValid(HttpServletRequest httpServletRequest);
}