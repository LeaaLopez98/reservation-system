package app.reservationsystem.clubs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClubResponseDTO {

    private Integer idClub;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<FieldResponseDTO> fields;

}
