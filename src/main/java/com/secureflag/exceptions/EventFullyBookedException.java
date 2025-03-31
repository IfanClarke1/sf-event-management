package com.secureflag.exceptions;

import lombok.Getter;

@Getter
public class EventFullyBookedException extends Exception{

    public EventFullyBookedException(String message) {
        super(message);
    }
}
