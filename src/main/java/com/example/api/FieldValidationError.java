package com.example.api;

/**
 * Created by szymon on 21.04.16.
 */
public class FieldValidationError {

    private String field;

    private String message;

    public FieldValidationError(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
