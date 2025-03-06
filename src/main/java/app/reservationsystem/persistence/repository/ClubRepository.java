package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    List<Club> findAllByOwnerIdUser(Long idOwner);
}
