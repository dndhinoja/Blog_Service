package org.dnd.rest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BlogAlreadyExist.class)
    public ResponseEntity<APIError> handleMyException1(BlogAlreadyExist b){
        List<String> details = new ArrayList<>();
        details.add(b.getLocalizedMessage());

        APIError apiError = new APIError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource already exist",
                details);
        return new ResponseEntity<APIError>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BlogNotFound.class)
    public ResponseEntity<APIError> handleMyException2(BlogNotFound b){
        List<String> details = new ArrayList<>();
        details.add(b.getLocalizedMessage());

        APIError apiError = new APIError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not exist",
                details);
        return new ResponseEntity<APIError>(apiError, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<>();
        details = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getObjectName()+" : "+err.getDefaultMessage())
                .collect(Collectors.toList());

        APIError apiError = new APIError(
                LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY,
                "Validation Error",
                details);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_GATEWAY);
    }
}
