package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class UnauthorizedException extends WebException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
