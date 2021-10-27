package br.com.carteiradoaposentado.infra.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String msg){
        super("Resource not found: " + msg);
    }
}
