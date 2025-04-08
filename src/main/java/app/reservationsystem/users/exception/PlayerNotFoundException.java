package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class PlayerNotFoundException extends GenericException {

    public PlayerNotFoundException(String msg) {
        super(msg, 404);
    }
}
