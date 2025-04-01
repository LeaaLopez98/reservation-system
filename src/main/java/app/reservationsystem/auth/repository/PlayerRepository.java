package app.reservationsystem.persistence.repository;

import app.reservationsystem.auth.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
