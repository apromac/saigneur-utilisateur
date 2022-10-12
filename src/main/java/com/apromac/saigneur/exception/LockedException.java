package com.apromac.saigneur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class LockedException extends RuntimeException {
    public LockedException(String msg) {
        super(msg);
    }
}
