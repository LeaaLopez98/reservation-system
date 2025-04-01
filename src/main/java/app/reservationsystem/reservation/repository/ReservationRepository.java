package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Reservation;
import app.reservationsystem.persistence.entity.Status;
import app.reservationsystem.persistence.projection.ReservationBetweenDatesProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByPlayerIdUser(Long idPlayer);
    List<Reservation> findAllByFieldClubOwnerIdUser(Long idUser);

    boolean existsByStatusNotLikeAndFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
            Status status,
            Integer idField,
            LocalDateTime dateEnd,
            LocalDateTime dateBegin
    );

    List<ReservationBetweenDatesProjection> findAllByStatusNotLikeAndField_Club_IdClubAndDateBeginAfterAndDateEndBefore(
            Status status,
            Integer idClub,
            LocalDateTime dateBegin,
            LocalDateTime dateEnd
    );



}
