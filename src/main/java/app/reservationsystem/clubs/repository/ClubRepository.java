package app.reservationsystem.clubs.repository;

import app.reservationsystem.clubs.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    List<Club> findAllByOwnerIdUser(Long idOwner);
}
