package app.reservationsystem.reservations.repository;

import app.reservationsystem.reservations.entity.Reservation;
import app.reservationsystem.reservations.entity.Status;
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

}
