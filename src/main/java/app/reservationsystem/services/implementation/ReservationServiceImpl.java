package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Field;
import app.reservationsystem.persistence.entity.Player;
import app.reservationsystem.persistence.entity.Reservation;
import app.reservationsystem.persistence.entity.Role;
import app.reservationsystem.persistence.repository.FieldRepository;
import app.reservationsystem.persistence.repository.PlayerRepository;
import app.reservationsystem.persistence.repository.ReservationRepository;
import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;
import app.reservationsystem.services.ReservationService;
import app.reservationsystem.util.ClaimsUtil;
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

    @Override
    public ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO) {

        boolean exists = reservationRepository.existsByFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
                reservationRequestDTO.getIdField(),
                reservationRequestDTO.getDateEnd(),
                reservationRequestDTO.getDateBegin()
        );

        Long idUser = ClaimsUtil.getUserId();

        if (exists) {
            throw new RuntimeException("Exists a reservation in that field in this period");
        }

        Player player = playerRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException(String.format("Player with id %s, Not found", idUser))
        );


        Field field = fieldRepository.findById(reservationRequestDTO.getIdField()).orElseThrow(
                () -> new RuntimeException(String.format("Field with id %s, Not found", reservationRequestDTO.getIdField()))
        );
        Reservation reservation = reservationMapper.dtoToEntity(reservationRequestDTO);

        reservation.setField(field);
        reservation.setPlayer(player);

        return reservationMapper.entityToDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponseDTO getReservationById(Long idReservation) {

        Reservation reservation = reservationRepository.findById(idReservation).orElseThrow(
                () -> new RuntimeException(String.format("Reservation with id %s, Not found", idReservation))
        );

        if (ClaimsUtil.getRole() == Role.PLAYER && !reservation.getPlayer().getIdUser().equals(ClaimsUtil.getUserId())) {
            throw new RuntimeException("You can't access this reservation");
        }
        else if (ClaimsUtil.getRole() == Role.OWNER && !reservation.getField().getClub().getOwner().getIdUser().equals(ClaimsUtil.getUserId())) {
            throw new RuntimeException("You can't access this reservation");
        }

        return reservationMapper.entityToDto(reservation);
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByRole() {

        List<Reservation> reservations;

        switch (ClaimsUtil.getRole()) {
            case PLAYER -> reservations = reservationRepository.findAllByPlayerIdUser(ClaimsUtil.getUserId());
            case OWNER -> reservations = reservationRepository.findAllByFieldClubOwnerIdUser(ClaimsUtil.getUserId());
            case ADMIN -> reservations = reservationRepository.findAll();
            default -> throw new RuntimeException("Invalid role");
        }

        return reservations
                .stream()
                .map(reservationMapper::entityToDto)
                .toList();
    }

    @Override
    public void deleteReservation(Long idReservation) {

        Long idUser = ClaimsUtil.getUserId();

        Reservation reservation = reservationRepository.findById(idReservation).orElseThrow(
                () -> new RuntimeException(String.format("Reservation with id %s, Not found", idReservation))
        );

        if (!reservation.getPlayer().getIdUser().equals(idUser)) {
            throw new RuntimeException("You are not the owner of this reservation");
        }

        reservationRepository.delete(reservation);
    }
}
