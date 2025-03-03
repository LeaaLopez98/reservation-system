package app.reservationsystem.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "idAdmin")
public class Owner extends UserAccount{

    @OneToMany(mappedBy = "admin")
    private List<Club> clubs;
}
