package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class BadRequestException extends WebException {

    public BadRequestException(String message) {
        super(message);
    }

}
