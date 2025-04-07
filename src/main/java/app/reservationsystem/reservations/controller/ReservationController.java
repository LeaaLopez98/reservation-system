package app.reservationsystem.reservation.controller;

import app.reservationsystem.reservation.dto.ReservationRequestDTO;
import app.reservationsystem.reservation.dto.ReservationResponseDTO;
import app.reservationsystem.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestBody ReservationRequestDTO reservationRequestDTO
    ) {
        return ResponseEntity.ok(reservationService.addReservation(reservationRequestDTO));
    }

    @GetMapping("/reservations/{id-reservation}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        return ResponseEntity.ok(reservationService.getReservationById(idReservation));
    }

    // TODO -> ADD FILTER BY QUERY PARAMS FOR idClub, idPlayer and idField
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(
    ) {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByRole();

        if (reservations.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("/reservations/{id-reservation}/cancel")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        reservationService.cancelReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reservations/{id-reservation}/confirm")
    public ResponseEntity<Void> confirmReservation(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        reservationService.confirmReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

}
