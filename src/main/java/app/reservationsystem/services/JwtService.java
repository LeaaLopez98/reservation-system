package app.reservationsystem.services;

import app.reservationsystem.persistence.entity.UserAccount;
import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(UserAccount account);
    boolean isValidToken(String token, String username);
    String extractUsername(String token);
    Claims getAllClaims(String token);

}
