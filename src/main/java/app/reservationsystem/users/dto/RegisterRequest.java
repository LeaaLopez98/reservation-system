package app.reservationsystem.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegisterRequest {

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Length(min = 3, message = "Username must be at least 3 characters long")
    String username;

    @NotBlank(message = "Password is required")
    @NotNull(message = "Password is required")
    @Length(min = 6, message = "Password must be at least 6 characters long")
    String password;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    String email;
}
