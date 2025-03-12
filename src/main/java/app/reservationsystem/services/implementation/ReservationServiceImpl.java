package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Field;
import app.reservationsystem.persistence.entity.Player;
import app.reservationsystem.persistence.entity.Reservation;
import app.reservationsystem.persistence.repository.FieldRepository;
import app.reservationsystem.persistence.repository.PlayerRepository;
import app.reservationsystem.persistence.repository.ReservationRepository;
import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;
import app.reservationsystem.services.interfaces.JwtService;
import app.reservationsystem.services.interfaces.ReservationService;
import app.reservationsystem.util.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PlayerRepository playerRepository;
    private final FieldRepository fieldRepository;

    private final ReservationMapper reservationMapper;

    private final JwtService jwtService;

    @Override
    public ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO, String token) {

        boolean exists = reservationRepository.existsByFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
                reservationRequestDTO.getIdField(),
                reservationRequestDTO.getDateEnd(),
                reservationRequestDTO.getDateBegin()
        );

        if (exists) {
            throw new RuntimeException("Exists a reservation in that field in this period");
        }

        Long idUser = jwtService.extractIdUser(token.substring(7));

        Field field = fieldRepository.findById(reservationRequestDTO.getIdField()).orElseThrow(
                () -> new RuntimeException(String.format("Field with id %s, Not found", reservationRequestDTO.getIdField()))
        );

        Player player = playerRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException(String.format("Player with id %s, Not found", idUser))
        );

        Reservation reservation = reservationMapper.dtoToEntity(reservationRequestDTO);

        reservation.setField(field);
        reservation.setPlayer(player);

        return reservationMapper.entityToDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponseDTO getReservationById(Long idReservation) {
        return reservationMapper.entityToDto(reservationRepository.findById(idReservation).orElseThrow(
                () -> new RuntimeException(String.format("Reservation with id %s, Not found", idReservation))
        ));
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations(String token) {

        // TODO -> ADD FILTER FOR idClub, idPlayer and idField
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::entityToDto)
                .toList();
    }

    @Override
    public void deleteReservation(Long idReservation, String token) {
        Long idUser = jwtService.extractIdUser(token.substring(7));

        Reservation reservation = reservationRepository.findById(idReservation).orElseThrow(
                () -> new RuntimeException(String.format("Reservation with id %s, Not found", idReservation))
        );

        if (!reservation.getPlayer().getIdUser().equals(idUser)) {
            throw new RuntimeException("You are not the owner of this reservation");
        }

        reservationRepository.delete(reservation);
    }
}
