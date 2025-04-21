package app.reservationsystem.reservations.service.impl;

import app.reservationsystem.clubs.entity.Field;
import app.reservationsystem.clubs.service.FieldService;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.entity.Reservation;
import app.reservationsystem.reservations.entity.Status;
import app.reservationsystem.shared.util.ClaimsUtil;
import app.reservationsystem.users.entity.Player;
import app.reservationsystem.users.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildReservation {

    private final PlayerService playerService;
    private final FieldService fieldService;

    public Reservation createReservation(ReservationRequestDTO reservationRequestDTO) {
        Long idUser = ClaimsUtil.getUserId();
        Player player = playerService.getPlayerEntityById(idUser);
        Field field = fieldService.getFieldEntityById(reservationRequestDTO.getIdField());

        return Reservation
                .builder()
                .player(player)
                .field(field)
                .status(Status.PENDING)
                .dateBegin(reservationRequestDTO.getDateBegin())
                .dateEnd(reservationRequestDTO.getDateEnd())
                .build();
    }

}
