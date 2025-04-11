package app.reservationsystem.reservations.service.impl;

import app.reservationsystem.clubs.entity.Field;
import app.reservationsystem.reservations.exception.ReservationNotAvailableException;
import app.reservationsystem.shared.exception.UnauthorizedAccessException;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.reservations.exception.ReservationNotFoundException;
import app.reservationsystem.users.entity.Player;
import app.reservationsystem.users.entity.Role;
import app.reservationsystem.clubs.service.FieldService;
import app.reservationsystem.reservations.repository.ReservationRepository;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.dto.ReservationResponseDTO;
import app.reservationsystem.reservations.entity.Reservation;
import app.reservationsystem.reservations.entity.Status;
import app.reservationsystem.reservations.service.ReservationService;
import app.reservationsystem.shared.util.ClaimsUtil;
import app.reservationsystem.reservations.mapper.ReservationMapper;
import app.reservationsystem.users.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PlayerService playerService;
    private final FieldService fieldService;

    private final Map<Integer, Object> fieldLocks = new ConcurrentHashMap<>();

    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResponseDTO addReservation(ReservationRequestDTO reservationRequestDTO) {

        Integer idField = reservationRequestDTO.getIdField();

        Object lock = fieldLocks.computeIfAbsent(idField, k -> new Object());

        synchronized (lock) {
            boolean exists = reservationRepository.existsByStatusNotLikeAndFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
                    Status.CANCELED,
                    reservationRequestDTO.getIdField(),
                    reservationRequestDTO.getDateEnd(),
                    reservationRequestDTO.getDateBegin()
            );

            if (exists) {
                throw new ReservationNotAvailableException(ExceptionMessages.RESERVATION_NOT_AVAILABLE);
            }

            Long idUser = ClaimsUtil.getUserId();
            Player player = playerService.getPlayerEntityById(idUser);

            Field field = fieldService.getFieldEntityById(reservationRequestDTO.getIdField());

            Reservation reservation = reservationMapper.dtoToEntity(reservationRequestDTO);

            reservation.setField(field);
            reservation.setPlayer(player);

            return reservationMapper.entityToDto(reservationRepository.save(reservation));
        }
    }

    @Override
    public ReservationResponseDTO getReservationById(Long idReservation) {

        Reservation reservation = getReservationEntityById(idReservation);

        if (isUserAuthorizedForReservation(reservation)) {
            throw new UnauthorizedAccessException(ExceptionMessages.RESERVATION_FORBIDDEN);
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
    public void cancelReservation(Long idReservation) {
        Long idUser = ClaimsUtil.getUserId();

        Reservation reservation = getReservationEntityById(idReservation);

        if (!reservation.getPlayer().getIdUser().equals(idUser)) {
            throw new UnauthorizedAccessException(ExceptionMessages.RESERVATION_NOT_OWNER);
        }

        reservation.setStatus(Status.CANCELED);

        reservationRepository.save(reservation);
    }

    @Override
    public void confirmReservation(Long idReservation) {

        Reservation reservation = getReservationEntityById(idReservation);

        reservation.setStatus(Status.CONFIRMED);

        reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationEntityById(Long idReservation) {
        return reservationRepository.findById(idReservation).orElseThrow(
                () -> new ReservationNotFoundException(String.format(ExceptionMessages.RESERVATION_NOT_FOUND, idReservation))
        );
    }

    private boolean isUserAuthorizedForReservation(Reservation reservation) {

        Long idUser = ClaimsUtil.getUserId();
        Role role = ClaimsUtil.getRole();

        return
                (role == Role.PLAYER && !reservation.getPlayer().getIdUser().equals(idUser)) ||
                (role == Role.OWNER && !reservation.getField().getClub().getOwner().getIdUser().equals(idUser));
    }

}
