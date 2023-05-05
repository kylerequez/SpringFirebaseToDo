package com.github.kylerequez.SpringFirebaseToDo.ErrorHandlers;

import com.github.kylerequez.SpringFirebaseToDo.Exceptions.DocumentDoesNotExistException;
import com.github.kylerequez.SpringFirebaseToDo.Exceptions.DocumentExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class FirebaseServiceErrorHandler {

    @ExceptionHandler(DocumentExistsException.class)
    public ResponseEntity<ErrorResponse> handleDocumentExistsExceptions(
            DocumentExistsException e
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace,
                        e.getData()
                ),
                status
        );
    }

    @ExceptionHandler(DocumentDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleDocumentDoesNotExistsExceptions(
            DocumentDoesNotExistException e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace
                ),
                status
        );
    }
}
