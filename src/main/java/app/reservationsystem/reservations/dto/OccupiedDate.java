package app.reservationsystem.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OccupiedDate {
    private LocalDateTime dateBegin;
    private LocalDateTime dateEnd;
}
