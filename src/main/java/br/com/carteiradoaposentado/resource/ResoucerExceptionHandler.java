package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.infra.exception.StandardError;
import br.com.carteiradoaposentado.infra.exception.ValidateError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResoucerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource nou found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<StandardError> resourceBussinesException(BussinesException e, HttpServletRequest request){
        String error = "Bussines exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateError> handle(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "Validate exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Set<JSONObject> erros = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> new JSONObject().put(erro.getField(), messageSource.getMessage(erro, LocaleContextHolder.getLocale())))
                .collect(Collectors.toSet());

        ValidateError err = new ValidateError(Instant.now(), status.value(), error, erros, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
