package app.reservationsystem.reservations.projection;

import java.time.LocalDateTime;

public record OccupiedDatesProjection(
        Integer fieldIdField,
        LocalDateTime dateBegin,
        LocalDateTime dateEnd
) {
}
