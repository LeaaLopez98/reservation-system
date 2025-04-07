package app.reservationsystem.users.exception;


import app.reservationsystem.shared.exception.GenericException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class UsernameNotFoundException extends GenericException {

    public UsernameNotFoundException(String msg) {
        super(msg, 404);
    }
}
