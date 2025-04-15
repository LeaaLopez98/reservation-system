package app.reservationsystem.reservations.service;

import app.reservationsystem.reservations.dto.ReservationOccupied;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.dto.ReservationResponseDTO;
import app.reservationsystem.reservations.entity.Reservation;

import java.util.List;

public interface ReservationService {

    ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO getReservationById(Long idReservation);

    List<ReservationResponseDTO> getReservationsByRole();

    void cancelReservation(Long idReservation);
    void confirmReservation(Long idReservation);

    List<ReservationOccupied> getOccupiedReservationsByWeek(Integer idClub);

    Reservation getReservationEntityById(Long idReservation);
}
