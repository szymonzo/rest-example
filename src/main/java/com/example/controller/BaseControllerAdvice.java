package com.example.controller;

import com.example.api.ApiBaseResponse;
import com.example.api.ApiResponseBuilder;
import com.example.api.ApiResponseStatus;
import com.example.api.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by szymon on 21.04.16.
 */
@ControllerAdvice
public class BaseControllerAdvice {

    @Autowired
    private ApiResponseBuilder builder;

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiBaseResponse exception(Exception ex) {
        ex.printStackTrace();
        return builder.error(ApiResponseStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiBaseResponse validation(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        return builder
                .error(new ServiceException(ApiResponseStatus.VALIDATION_ERROR, ex.getBindingResult()));
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiBaseResponse serviceException(ServiceException ex) {
        ex.printStackTrace();
        return builder.error(ex);
    }

}
