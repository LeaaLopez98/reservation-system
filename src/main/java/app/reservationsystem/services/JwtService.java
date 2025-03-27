package app.reservationsystem.services.interfaces;

import app.reservationsystem.persistence.entity.Role;
import app.reservationsystem.persistence.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserAccount account);
    boolean isValidToken(String token, UserDetails userDetails);
    String extractUsername(String token);
    Role extractRole(String token);
    Long extractIdUser(String token);

}
