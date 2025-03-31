package com.secureflag.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

}
