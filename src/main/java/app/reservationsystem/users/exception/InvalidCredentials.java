package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class InvalidCredentials extends GenericException {

    public InvalidCredentials(String msg) {
        super(msg, 401);
    }

}
