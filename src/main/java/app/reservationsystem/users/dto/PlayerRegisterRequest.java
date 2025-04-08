package app.reservationsystem.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class PlayerRegisterRequest extends RegisterRequest {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Length(min = 3, message = "Name must be at least 3 characters long")
    private String name;
}
