package com.eazybytes.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eazybytes.accounts.dto.ErrorResponseDTO;

// ControllerAdvice is used to handle exceptions globally in the application, any exception handled here will be applicable to all controllers in the application
@ControllerAdvice
public class GobalExceptionHandler {
    // webRequest to get API info, check the ErrorResponseDTO class for more info
    // false value is used to get the API path without the query parameters
    @ExceptionHandler (CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception, 
                                                                        WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            webRequest.getDescription(false),
            HttpStatus.BAD_REQUEST,
            exception.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return  new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }

}
