package app.reservationsystem.clubs.repository;

import app.reservationsystem.clubs.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    List<Field> findAllByClubIdClub(Integer idClub);

}
