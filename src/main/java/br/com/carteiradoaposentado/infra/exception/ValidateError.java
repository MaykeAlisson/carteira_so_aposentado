package br.com.carteiradoaposentado.infra.exception;

import org.json.JSONObject;

import java.time.Instant;
import java.util.Set;

public class ValidateError {

    private final String fild;
    private final String error;

    public ValidateError(String fild, String error) {
        this.fild = fild;
        this.error = error;
    }

    public String getFild() {
        return fild;
    }


    public String getError() {
        return error;
    }

}
