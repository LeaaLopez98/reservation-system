package app.reservationsystem.clubs.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ClubNotFoundException extends GenericException {

    public ClubNotFoundException(String msg) {
        super(msg, 404);
    }
}
