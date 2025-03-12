package app.reservationsystem.presentation.controller;

import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;
import app.reservationsystem.services.interfaces.ReservationService;
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
            @RequestBody ReservationRequestDTO reservationRequestDTO,
            @RequestHeader(name = "Authorization") String token
    ) {
        return ResponseEntity.ok(reservationService.addReservation(reservationRequestDTO, token));
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
            @RequestHeader(name = "Authorization") String token

    ) {

        List<ReservationResponseDTO> reservations = reservationService.getAllReservations(token);

        if (reservations.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{id-reservation}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable(name = "id-reservation") Long idReservation,
            @RequestHeader(name = "Authorization") String token
    ) {
        reservationService.deleteReservation(idReservation, token);
        return ResponseEntity.noContent().build();
    }

}
