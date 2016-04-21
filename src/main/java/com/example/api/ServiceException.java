package com.example.api;

/**
 * Created by szymon on 21.04.16.
 */
public class ServiceException extends RuntimeException {

    private ApiResponseStatus status;

    private String message;

    private Object data;

    public ServiceException(ApiResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServiceException(ApiResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ServiceException(ApiResponseStatus status) {
        this.status = status;
    }

    public ApiResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ApiResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
