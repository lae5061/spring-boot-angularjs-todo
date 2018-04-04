package com.buildourweb.springbootangularjstodo.model;

import org.springframework.validation.ObjectError;

import java.util.List;

public class Message {

    public static Message NO_ERROR_MESSAGE = new Message(false, null, null);

    private final boolean error;
    private final String message;
    private final List<ObjectError> objectErrors;

    public Message(final boolean error, String message, final List<ObjectError> objectErrors) {
        this.error = error;
        this.message = message;
        this.objectErrors = objectErrors;
    }

    public boolean isError() {
        return error;
    }

    public List<ObjectError> getObjectErrors() {
        return objectErrors;
    }

    public String getMessage() {
        return message;
    }
}
