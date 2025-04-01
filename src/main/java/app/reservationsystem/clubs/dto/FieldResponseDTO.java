package app.reservationsystem.clubs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldResponseDTO {

    private Integer idField;
    private Integer idClub;
    private Integer fieldNumber;
    private Integer capacity;
    private Float price;
    private String address;

}
