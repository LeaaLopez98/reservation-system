package app.reservationsystem.shared.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class GenericException extends RuntimeException{

    private final Integer statusCode;

    public GenericException(String msg, Integer statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

}
