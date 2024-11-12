package xyz.artsna.goodel.domain.exceptions;

import xyz.artsna.goodel.infra.exception.WebException;

public class InternalServerErrorException extends WebException {

    public InternalServerErrorException(String message) {
        super(message);
    }

}
