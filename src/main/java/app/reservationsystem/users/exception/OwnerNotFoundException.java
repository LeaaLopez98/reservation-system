package app.reservationsystem.users.exception;

import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class OwnerNotFoundException extends GenericException {

    public OwnerNotFoundException(String msg) {
        super(msg, 404);
    }
}
