package app.reservationsystem.persistence.repository;

import app.reservationsystem.persistence.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
}
