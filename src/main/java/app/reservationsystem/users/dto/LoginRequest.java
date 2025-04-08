package app.reservationsystem.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank(message = "Username is required")
    @NotNull(message = "Username is required")
    String username;

    @NotBlank(message = "Password is required")
    @NotNull(message = "Password is required")
    String password;
}
