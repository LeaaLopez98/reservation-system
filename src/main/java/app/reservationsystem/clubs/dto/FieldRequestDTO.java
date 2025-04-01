package app.reservationsystem.clubs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldRequestDTO {
    private Integer fieldNumber;
    private Integer capacity;
    private Float price;
}
