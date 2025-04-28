package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class EmailNotFoundException extends GenericException {

    public EmailNotFoundException(String msg) {
        super(msg, 409);
    }
}
