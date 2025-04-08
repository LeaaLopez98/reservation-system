package app.reservationsystem.clubs.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ClubUpdateDTO {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;

    @Email(message = "Invalid email address")
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
