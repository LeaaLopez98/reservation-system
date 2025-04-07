package app.reservationsystem.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    String username;
    String password;
}
