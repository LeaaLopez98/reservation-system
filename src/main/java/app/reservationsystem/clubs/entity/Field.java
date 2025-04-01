package app.reservationsystem.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "field_entity")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idField;

    @Column(nullable = false)
    private Integer fieldNumber;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(name = "idClub")
    private Club club;
}
