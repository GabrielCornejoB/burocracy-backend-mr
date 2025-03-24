package co.com.bancolombia.config;

import co.com.bancolombia.utils.exceptions.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(GeneralException ex) {

        return ResponseEntity
                .status(ex.getHttpStatusCode().getCode())
                .body(new ErrorResponse(ex.getMessage(), ex.getHttpStatusCode().getCode()));
    }

    private record ErrorResponse(String message, int statusCode) {
    }
    
}
