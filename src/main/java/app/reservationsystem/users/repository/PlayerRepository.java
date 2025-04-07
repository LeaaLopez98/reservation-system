package app.reservationsystem.auth.repository;

import app.reservationsystem.auth.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
