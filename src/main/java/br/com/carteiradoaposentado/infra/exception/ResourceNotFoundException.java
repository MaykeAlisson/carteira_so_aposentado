package br.com.carteiradoaposentado.infra.exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String msg) {
        super(format("Resource not found: %s", msg));
    }
}
