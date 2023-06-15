package com.canse.discord.handlers;

import com.canse.discord.exceptions.ObjectValidationException;
import com.canse.discord.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(ObjectValidationException.class)
        public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){

            ExceptionRepresentation representation = ExceptionRepresentation.builder()
                    .errorMessage("Object not valid exception has occured")
                    .errorSource(exception.getViolationSource())
                    .validationErrors(exception.getViolations())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(representation);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ExceptionRepresentation> handlerException(EntityNotFoundException exception){

            ExceptionRepresentation representation = ExceptionRepresentation.builder()
                    .errorMessage(exception.getMessage())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(representation);
        }

        @ExceptionHandler(OperationNonPermittedException.class)
        public ResponseEntity<ExceptionRepresentation> handlerException(OperationNonPermittedException exception){

            ExceptionRepresentation representation = ExceptionRepresentation.builder()
                    .errorMessage(exception.getErrorMsg())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(representation);
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ExceptionRepresentation> handlerException(DataIntegrityViolationException exception){

            ExceptionRepresentation representation = ExceptionRepresentation.builder()
                    .errorMessage("Probleme d'integrité de la donnée en base ")
                    .build();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(representation);
        }




}
