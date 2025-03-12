package app.reservationsystem.services.interfaces;

import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {

    ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO, String token);
    ReservationResponseDTO getReservationById(Long idReservation);

    List<ReservationResponseDTO> getAllReservations(String token);

    void deleteReservation(Long idReservation, String token);
}
