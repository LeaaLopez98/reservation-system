package app.reservationsystem.reservation.entity;

import app.reservationsystem.auth.entity.Player;
import app.reservationsystem.clubs.entity.Field;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "idField")
    private Field field;

    @Column(nullable = false)
    private LocalDateTime dateBegin;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

}
