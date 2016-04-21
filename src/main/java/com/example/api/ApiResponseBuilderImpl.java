package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by szymon on 21.04.16.
 */
@Component
public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ApiBaseResponse success() {
        return new ApiBaseResponse(ApiResponseStatus.SUCCESS);
    }

    @Override
    public ApiBaseResponse error(ServiceException exception) {
        ApiBaseResponse response = new ApiBaseResponse(exception.getStatus());
        response.setData(exception.getData());
        if (exception.getStatus() == ApiResponseStatus.VALIDATION_ERROR) {
            if (exception.getData() instanceof BindingResult) {
                response.setData(this.mapBindingResult((BindingResult) exception.getData()));
            }
        }
        this.setMessage(response);
        return response;
    }

    @Override
    public ApiBaseResponse success(Object data) {
        return new ApiBaseResponse(data, ApiResponseStatus.SUCCESS);
    }

    @Override
    public ApiBaseResponse error(ApiResponseStatus apiResponseStatus) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse(apiResponseStatus);
        this.setMessage(apiBaseResponse);
        return apiBaseResponse;
    }

    private void setMessage(ApiBaseResponse<?> response) {
        if (response.getStatus() != null) {
            try {
                Locale locale = new Locale("en");
                response.setMessage(messageSource.getMessage(response.getStatus().name(), null, locale));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<FieldValidationError> mapBindingResult(BindingResult bindingResult) {
        List<FieldValidationError> list = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            FieldValidationError fieldValidationError =
                    new FieldValidationError(error.getField(), error.getDefaultMessage());
            list.add(fieldValidationError);
        }
        return list;
    }
}
