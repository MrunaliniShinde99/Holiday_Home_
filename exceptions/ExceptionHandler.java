package com.holiday.home.exceptions;

import com.holiday.home.dto.BasicResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {AuthException.class, BadCredentialsException.class})
    public ResponseEntity<BasicResponseDTO<AuthException>> handlerAuthException(AuthException e){
        return  ResponseEntity.ok(new BasicResponseDTO<>( false, e.getMessage(), e ));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FileUploadException.class)
    public ResponseEntity<BasicResponseDTO<FileUploadException>> handlerFileUploadException(FileUploadException e){
        return  ResponseEntity.ok(new BasicResponseDTO<>( false, e.getMessage(), e ));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BasicResponseDTO<ResourceNotFoundException>> handlerResourceNotFoundException(ResourceNotFoundException e){
        return  ResponseEntity.ok(new BasicResponseDTO<>( false, e.getMessage(), e ));
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<BasicResponseDTO<DateTimeParseException>> handlerDateTimeParseException(DateTimeParseException e){
        return  ResponseEntity.ok(new BasicResponseDTO<>( false, e.getMessage(), e ));
    }

}


