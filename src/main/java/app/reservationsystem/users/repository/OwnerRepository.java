package app.reservationsystem.users.repository;

import app.reservationsystem.users.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
