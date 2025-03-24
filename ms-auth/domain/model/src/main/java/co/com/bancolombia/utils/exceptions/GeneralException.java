package co.com.bancolombia.utils.exceptions;

import co.com.bancolombia.utils.enums.HttpStatusCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends RuntimeException {

    private final HttpStatusCode httpStatusCode;

    public GeneralException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
