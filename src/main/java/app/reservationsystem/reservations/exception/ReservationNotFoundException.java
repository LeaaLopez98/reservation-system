package app.reservationsystem.reservations.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ReservationNotFoundException extends GenericException {

    public ReservationNotFoundException(String msg) {
        super(msg, 404);
    }

}
