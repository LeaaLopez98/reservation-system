package app.reservationsystem.auth.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {
    String username;
    String password;
    String email;
}
