package com.example.api;

/**
 * Created by szymon on 21.04.16.
 */
public interface ApiResponseBuilder {

    ApiBaseResponse success();

    ApiBaseResponse error(ServiceException exception);

    ApiBaseResponse error(ApiResponseStatus apiResponseStatus);

    ApiBaseResponse success(Object data);

}
