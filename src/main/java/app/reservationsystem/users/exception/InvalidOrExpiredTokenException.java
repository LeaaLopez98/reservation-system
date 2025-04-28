package app.reservationsystem.users.exception;


import app.reservationsystem.shared.exception.GenericException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class InvalidOrExpiredTokenException extends GenericException {

    public InvalidOrExpiredTokenException(String msg) {
        super(msg, 403);
    }

}
