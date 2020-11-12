package br.com.casadocodigo.error;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorModel> handle(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body(
                        ErrorModel.builder()
                                .errors(
                                        ex.getBindingResult()
                                                .getFieldErrors()
                                                .stream()
                                                .map(error ->
                                                        String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                                                .collect(Collectors.toList()))
                                .build());
    }
}
