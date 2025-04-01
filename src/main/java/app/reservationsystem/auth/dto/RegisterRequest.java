package app.reservationsystem.presentation.dto.auth;

import lombok.Getter;

@Getter
public class RegisterRequest {
    String username;
    String password;
    String email;
}
