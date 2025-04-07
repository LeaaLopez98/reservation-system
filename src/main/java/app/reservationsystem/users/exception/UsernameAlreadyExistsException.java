package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class UsernameAlreadyExistsException extends GenericException {

    public UsernameAlreadyExistsException(String msg) {
        super(msg, 409);
    }


}
