package app.reservationsystem.reservations.service.impl;

import app.reservationsystem.clubs.service.ClubService;
import app.reservationsystem.emails.builder.ConfirmReservationBuilder;
import app.reservationsystem.emails.service.EmailService;
import app.reservationsystem.reservations.dto.OccupiedDate;
import app.reservationsystem.reservations.dto.ReservationOccupied;
import app.reservationsystem.reservations.exception.ReservationNotAvailableException;
import app.reservationsystem.reservations.projection.OccupiedDatesProjection;
import app.reservationsystem.shared.exception.UnauthorizedAccessException;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.reservations.exception.ReservationNotFoundException;
import app.reservationsystem.users.entity.Role;
import app.reservationsystem.reservations.repository.ReservationRepository;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.dto.ReservationResponseDTO;
import app.reservationsystem.reservations.entity.Reservation;
import app.reservationsystem.reservations.entity.Status;
import app.reservationsystem.reservations.service.ReservationService;
import app.reservationsystem.shared.util.ClaimsUtil;
import app.reservationsystem.reservations.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BuildReservation buildReservation;

    private final ClubService clubService;
    private final EmailService emailService;

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

            Reservation savedReservation = reservationRepository
                    .save(buildReservation.createReservation(reservationRequestDTO));

            emailService.sendEmail(new ConfirmReservationBuilder(savedReservation));

            return reservationMapper.entityToDto(savedReservation);
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
    public List<ReservationOccupied> getOccupiedReservationsByWeek(Integer idClub) {

        clubService.checkIfClubExists(idClub);

        List<OccupiedDatesProjection> reservations = reservationRepository.findAllByStatusNotLikeAndFieldClubIdClubAndDateBeginAfterAndDateEndBefore(
                Status.CANCELED,
                idClub,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7)
        );

        return reservations.stream()
                .collect(
                        Collectors.groupingBy(
                            OccupiedDatesProjection::fieldIdField,
                            Collectors.mapping(
                                record -> new OccupiedDate(record.dateBegin(), record.dateEnd()),
                                Collectors.toList())))
                .entrySet()
                .stream()
                .map(entry -> new ReservationOccupied(entry.getKey(), entry.getValue()))
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
