package app.reservationsystem.clubs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClubRequestDTO {

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Address is required")
    @NotNull(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Opening time is required")
    private LocalTime openingTime;

    @NotNull(message = "Closing time is required")
    private LocalTime closingTime;
}
