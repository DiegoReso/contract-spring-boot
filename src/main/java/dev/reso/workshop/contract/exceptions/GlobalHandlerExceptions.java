package dev.reso.workshop.contract.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.time.Instant;

@ControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(FileEmptyException.class)
    public ResponseEntity<StandardError> handlerFileEmpty(FileEmptyException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("File is empty");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<StandardError> handlerEntityNotFound(EntityNotFound e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<StandardError> handlerMaxSizeUploadExceed(MaxUploadSizeExceededException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.PAYLOAD_TOO_LARGE.value());
        err.setError("File is empty");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(err);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<StandardError> handlerFileStorageException(FileStorageException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Could not store file");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler({IOException.class, JsonParseException.class, JsonMappingException.class})
    public ResponseEntity<StandardError> handleJsonProcessingException(IOException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Json error");

        String errorMessage = "Request data is poorly formatted or invalid.";
        if (e instanceof JsonParseException) {
            errorMessage = "Syntax error in JSON: " + e.getMessage();
        } else if (e instanceof JsonMappingException) {
            errorMessage = "Error mapping JSON data to object: " + e.getMessage();
        }
        err.setMessage(errorMessage);
        err.setPath(request.getRequestURI());

        System.err.println("JSON Processing Error: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ContractValidationException.class)
    public ResponseEntity<StandardError> handlerContractValidation(ContractValidationException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Validation failed");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
