package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByPlayerIdUser(Long idPlayer);
    List<Reservation> findAllByFieldIdField(Integer idField);

    @Query(
            value = """
            select r.*
            from reservation r
            join field f on r.id_field = f.id_field
            join club c on f.id_club = c.id_club
            where c.id_club = :idClub
            """,
            nativeQuery = true
    )
    List<Reservation> findAllByClub(Integer idClub);

    boolean existsByFieldIdFieldAndDateBeginBeforeAndDateEndAfter(
            Integer idField,
            LocalDateTime dateEnd,
            LocalDateTime dateBegin
    );
}
