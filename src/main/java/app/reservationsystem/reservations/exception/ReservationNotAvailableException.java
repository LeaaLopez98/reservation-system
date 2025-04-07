package app.reservationsystem.reservations.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ReservationNotAvailableException extends GenericException {

    public ReservationNotAvailableException(String msg) {
        super(msg, 409);
    }
}
