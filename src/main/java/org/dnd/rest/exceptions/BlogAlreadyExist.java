package org.dnd.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST )
public class BlogAlreadyExist extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BlogAlreadyExist(String message) {
        super(message);
    }
}
