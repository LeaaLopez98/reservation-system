package app.reservationsystem.users.repository;

import app.reservationsystem.users.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
