package app.reservationsystem.reservation.dto;

import app.reservationsystem.reservation.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponseDTO {

    private Long idReservation;
    private String clubName;
    private String playerName;
    private String address;
    private Integer fieldNumber;
    private LocalDateTime dateBegin;
    private LocalDateTime dateEnd;

    private Status status;

}
