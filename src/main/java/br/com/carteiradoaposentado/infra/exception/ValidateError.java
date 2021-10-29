package br.com.carteiradoaposentado.infra.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Set;

public class ValidateError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private Set<JSONObject> message;
    private String path;

    public ValidateError(
            Instant timestamp,
            Integer status,
            String error,
            Set<JSONObject> message,
            String path
    ) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Set<JSONObject> getMessage() {
        return message;
    }

    public void setMessage(Set<JSONObject> message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
