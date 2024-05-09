package com.servicestationmanagement.advices;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.servicestationmanagement.exception.BadRequestException;
import com.servicestationmanagement.exception.InternalServerException;
import com.servicestationmanagement.exception.NotFoundException;

@RestControllerAdvice
public class ApplicationAdvice {
     @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationError handleNotFoundException(NotFoundException ex){
        return new ApplicationError(
          ex.getMessage(),
          LocalDate.now(),
                HttpStatus.NOT_FOUND.value()
        );
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationError handleBadRequestException(BadRequestException ex){
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApplicationError handleInternalServerException(InternalServerException ex){
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }
}
