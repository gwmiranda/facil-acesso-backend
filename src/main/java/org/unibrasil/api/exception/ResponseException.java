package org.unibrasil.api.exception;

import jakarta.ws.rs.WebApplicationException;

public class ResponseException extends WebApplicationException {

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }
}