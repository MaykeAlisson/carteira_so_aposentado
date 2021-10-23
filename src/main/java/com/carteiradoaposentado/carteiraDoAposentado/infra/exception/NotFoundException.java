package com.carteiradoaposentado.carteiraDoAposentado.infra.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final Object id){
        super("Resource not found. Id " + id);
    }
}
