package app.reservationsystem.persistence.repository;

import app.reservationsystem.auth.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
