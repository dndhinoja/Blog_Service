package org.dnd.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogNotFound extends Exception{
    public BlogNotFound(String message) {
        super(message);
    }
}
