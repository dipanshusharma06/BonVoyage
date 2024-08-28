package com.gl.app.bookingService.exception;

import com.gl.app.bookingService.dto.ErrorDetailsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetailsResponse errorResponse = new ErrorDetailsResponse(
                LocalDate.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error->{
                    String field= ((FieldError)error).getField();
                    String message= error.getDefaultMessage();
                    errors.put(field,message);
                }
        );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}