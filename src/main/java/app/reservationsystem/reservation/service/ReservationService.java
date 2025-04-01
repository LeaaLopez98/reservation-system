package app.reservationsystem.services;

import app.reservationsystem.presentation.dto.reservations.OccupiedReservationDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {

    ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO getReservationById(Long idReservation);

    List<ReservationResponseDTO> getReservationsByRole();

    List<OccupiedReservationDTO> getReservationsOccupiedPerWeekByClub(Integer idClub);

    void cancelReservation(Long idReservation);
    void confirmReservation(Long idReservation);
}
