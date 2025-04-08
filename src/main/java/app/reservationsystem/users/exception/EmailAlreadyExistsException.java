package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class EmailAlreadyExistsException extends GenericException {

    public EmailAlreadyExistsException(String msg) {
        super(msg, 409);
    }
}
