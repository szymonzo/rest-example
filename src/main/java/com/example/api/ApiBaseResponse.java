package com.example.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by szymon on 21.04.16.
 */
public class ApiBaseResponse<T> {




    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ApiResponseStatus status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    private Date timeStamp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public ApiBaseResponse(T data, ApiResponseStatus status) {
        this.data = data;
        this.status = status;
        this.timeStamp = new Date();
    }

    public ApiBaseResponse(ApiResponseStatus status) {
        this.status = status;
        this.timeStamp = new Date();
    }

    public ApiBaseResponse( ) {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApiBaseResponse{");
        sb.append("data=").append(data);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
