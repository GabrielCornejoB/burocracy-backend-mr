package co.com.bancolombia.utils.enums;

import lombok.Getter;

@Getter
public enum HttpStatusCode {

    BAD_REQUEST(400), UNAUTHORIZED(401), FORBIDDEN(403), NOT_FOUND(404), CONFLICT(409);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }
}
