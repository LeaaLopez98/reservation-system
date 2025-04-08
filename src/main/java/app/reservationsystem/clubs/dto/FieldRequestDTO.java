package app.reservationsystem.clubs.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldRequestDTO {

    @NotNull(message = "Field number is required")
    private Integer fieldNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 5, message = "Capacity must be at least 5")
    @Max(value = 11, message = "Capacity must be at most 11")
    private Integer capacity;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price is required")
    private Float price;
}
