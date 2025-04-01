package app.reservationsystem.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "idOwner")
public class Owner extends UserAccount{

    @OneToMany(mappedBy = "owner")
    private List<Club> clubs;
}
