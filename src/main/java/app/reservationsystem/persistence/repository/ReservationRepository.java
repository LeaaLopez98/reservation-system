package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByPlayerIdUser(Long idPlayer);
    List<Reservation> findAllByFieldClubOwnerIdUser(Long idUser);

    boolean existsByFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
            Integer idField,
            LocalDateTime dateEnd,
            LocalDateTime dateBegin
    );
}
