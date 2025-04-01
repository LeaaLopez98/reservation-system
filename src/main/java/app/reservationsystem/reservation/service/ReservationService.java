package app.reservationsystem.reservation.service;

import app.reservationsystem.reservation.dto.ReservationRequestDTO;
import app.reservationsystem.reservation.dto.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {

    ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO getReservationById(Long idReservation);

    List<ReservationResponseDTO> getReservationsByRole();

    void cancelReservation(Long idReservation);
    void confirmReservation(Long idReservation);
}
