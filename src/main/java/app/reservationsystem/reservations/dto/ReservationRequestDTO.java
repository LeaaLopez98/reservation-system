package app.reservationsystem.reservation.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequestDTO {

    private Integer idField;
    private LocalDateTime dateBegin;
    private LocalDateTime dateEnd;

}
