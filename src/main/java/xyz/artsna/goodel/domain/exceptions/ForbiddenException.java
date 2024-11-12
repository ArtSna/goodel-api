package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class ForbiddenException extends WebException {

    public ForbiddenException(String message) {
        super(message);
    }

}
