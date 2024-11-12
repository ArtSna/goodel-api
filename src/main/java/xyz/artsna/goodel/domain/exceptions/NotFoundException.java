package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class NotFoundException extends WebException {

    public NotFoundException(String message) {
        super(message);
    }

}
