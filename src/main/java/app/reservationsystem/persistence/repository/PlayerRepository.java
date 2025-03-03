package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
