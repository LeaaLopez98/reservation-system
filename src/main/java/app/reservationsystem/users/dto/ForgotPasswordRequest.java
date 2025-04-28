package app.reservationsystem.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequest (
        @NotNull(message = "Email is required")
        @Email(message = "Invalid email address")
        @NotBlank(message = "Email is required")
        String email
) {
}
