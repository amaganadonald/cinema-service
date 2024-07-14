package com.amagana.cinema_service.exception.handler;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amagana.cinema_service.exception.CinemaBussnessException;
import com.amagana.cinema_service.exception.TicketPriceNotEqualsProjectionException;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.model.ErrorsResponse;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleEntityNotFound(EntityNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.apiResponseMessage(
            HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<ErrorsResponse>> handlerValidation(MethodArgumentNotValidException exception){
        List<ErrorsResponse> errors = exception.getFieldErrors().stream()
                                               .map(error-> new ErrorsResponse(error.getField(), error.getDefaultMessage()))
                                               .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            APIResponse.apiResponseErrors(HttpStatus.BAD_REQUEST.value(), errors)
        );
    }

    @ExceptionHandler(CinemaBussnessException.class)
    public ResponseEntity<APIResponse<String>> handleGlobalException(CinemaBussnessException exceptionHandler){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            APIResponse.apiResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exceptionHandler.getMessage())
        );
    }

    @ExceptionHandler(TicketPriceNotEqualsProjectionException.class)
    public ResponseEntity<APIResponse<String>> handleTicketPrice(TicketPriceNotEqualsProjectionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.apiResponseMessage(
            HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse<String>> handleDataIntegrity(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(APIResponse.apiResponseMessage(
            HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }
}
