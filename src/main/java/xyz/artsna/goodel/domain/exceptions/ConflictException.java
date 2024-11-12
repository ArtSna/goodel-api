package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class ConflictException extends WebException {

    public ConflictException(String message) {
        super(message);
    }

}
