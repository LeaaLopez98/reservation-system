package app.reservationsystem.auth.entity;

import app.reservationsystem.reservation.entity.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@PrimaryKeyJoinColumn(name = "idPlayer")
public class Player extends UserAccount{

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "player")
    private List<Reservation> reservations;

}
