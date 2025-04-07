package app.reservationsystem.clubs.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class FieldNotFoundException extends GenericException {

    public FieldNotFoundException(String msg) {
        super(msg, 404);
    }

}
