package com.github.kylerequez.SpringFirebaseToDo.Exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DocumentExistsException extends RuntimeException{
    private HttpStatus status = null;
    private Object data = null;

    public DocumentExistsException(
            String message
    ) {
        super(message);
    }

    public DocumentExistsException(
            HttpStatus status,
            String message
    ) {
        this(message);
        this.status = status;
    }

    public DocumentExistsException(
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
