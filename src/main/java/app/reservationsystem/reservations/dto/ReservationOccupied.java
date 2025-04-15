package app.reservationsystem.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReservationOccupied {
    private Integer idField;
    List<OccupiedDate> occupiedDate;
}
