package app.reservationsystem.presentation.dto.auth;

import lombok.Getter;

@Getter
public class LoginRequest {
    String username;
    String password;
}
