package app.reservationsystem.auth.service;

import app.reservationsystem.auth.entity.UserAccount;
import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(UserAccount account);
    boolean isValidToken(String token, String username);
    String extractUsername(String token);
    Claims getAllClaims(String token);

}
