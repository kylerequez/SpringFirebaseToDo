package com.github.kylerequez.SpringFirebaseToDo.Exceptions;

import org.springframework.http.HttpStatus;

public class DocumentDoesNotExistException extends RuntimeException{
    private HttpStatus status = null;
    private Object data = null;

    public DocumentDoesNotExistException(
            String message
    ) {
        super(message);
    }

    public DocumentDoesNotExistException(
            HttpStatus status,
            String message
    ) {
        this(message);
        this.status = status;
    }

    public DocumentDoesNotExistException(
            HttpStatus status,
            String message,
            Object data
    ) {
        this(
                status,
                message
        );
        this.data = data;
    }
}
