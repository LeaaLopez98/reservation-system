package app.reservationsystem.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ResetPasswordRequest (
        @NotNull(message = "Token is required")
        @NotBlank(message = "Token is required")
        String token,

        @NotNull(message = "Password is required")
        @NotBlank(message = "Password is required")
        @Length(min = 7, message = "Password must be at least 6 characters long")
        String password
) {
}
