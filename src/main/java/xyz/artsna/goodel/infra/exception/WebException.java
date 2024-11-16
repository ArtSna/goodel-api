package xyz.artsna.goodel.infra.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebException extends RuntimeException {

    public WebException(String message) {
        super(message);
    }

}
