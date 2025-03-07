package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    List<Field> findAllByClubIdClub(Integer idClub);

}
