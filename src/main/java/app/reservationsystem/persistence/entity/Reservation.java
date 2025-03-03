package app.reservationsystem.persistence.entity;

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
    private Integer idReservation;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "idField")
    private Field field;

    private LocalDateTime reservationDate;

}
