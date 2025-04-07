package app.reservationsystem.shared.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class UnauthorizedAccessException extends GenericException {

    public UnauthorizedAccessException(String msg) {
        super(msg, 403);
    }
}
