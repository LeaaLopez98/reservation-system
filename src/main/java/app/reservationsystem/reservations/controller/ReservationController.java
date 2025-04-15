package app.reservationsystem.reservations.controller;

import app.reservationsystem.reservations.dto.ReservationOccupied;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.dto.ReservationResponseDTO;
import app.reservationsystem.reservations.service.ReservationService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody ReservationRequestDTO reservationRequestDTO
    ) {
        return ResponseEntity.ok(reservationService.addReservation(reservationRequestDTO));
    }

    @GetMapping("/reservations/{id-reservation}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
            @PathVariable(name = "id-reservation") Long idReservation
    ) {
        return ResponseEntity.ok(reservationService.getReservationById(idReservation));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(
    ) {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByRole();

        if (reservations.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("clubs/{id-club}/reservations/weekly")
    public ResponseEntity<List<ReservationOccupied>> getOccupiedReservationsByWeek(
            @PathVariable(name = "id-club") Integer idClub
    ) {
        return ResponseEntity.ok(reservationService.getOccupiedReservationsByWeek(idClub));
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
