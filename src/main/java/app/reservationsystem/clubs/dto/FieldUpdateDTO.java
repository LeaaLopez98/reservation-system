package app.reservationsystem.clubs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FieldUpdateDTO {
    @Min(value = 5, message = "Capacity must be at least 5")
    @Max(value = 11, message = "Capacity must be at most 11")
    private Integer capacity;

    @Positive(message = "Price must be positive")
    private Float price;
}
