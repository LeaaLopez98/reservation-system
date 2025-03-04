package app.reservationsystem.services.interfaces;

import app.reservationsystem.persistence.entity.UserAccount;

public interface JwtService {

    String generateToken(UserAccount account);
    String validateToken(String token);

}
