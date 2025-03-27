package app.reservationsystem.presentation.controller;

import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;
import app.reservationsystem.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestBody ReservationRequestDTO reservationRequestDTO
    ) {
        return ResponseEntity.ok(reservationService.addReservation(reservationRequestDTO));
    }

    @GetMapping("/{id-reservation}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        return ResponseEntity.ok(reservationService.getReservationById(idReservation));
    }

    // TODO -> ADD FILTER BY QUERY PARAMS FOR idClub, idPlayer and idField
    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(
    ) {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByRole();

        if (reservations.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("/{id-reservation}/cancel")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        reservationService.cancelReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id-reservation}/confirm")
    public ResponseEntity<Void> confirmReservation(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        reservationService.confirmReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

}
