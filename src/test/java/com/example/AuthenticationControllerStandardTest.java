package com.example;

import com.example.api.ApiBaseResponse;
import com.example.entity.api.AuthenticationOut;
import com.example.entity.api.StandardAuthenticationIn;
import com.google.common.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Header;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by szymon on 21.04.16.
 */
@IntegrationTest()
public class AuthenticationControllerStandardTest extends RestFundamentalApplicationTests{


    @Test
    public void testStandardAuthentication() throws Exception {

        StandardAuthenticationIn standardAuthenticateIn = new StandardAuthenticationIn();
        standardAuthenticateIn.setPassword("adam123");
        standardAuthenticateIn.setLogin("jestemAdam");
        Object authenticationOut =
                given().
                        contentType(ContentType.JSON).
                        body(standardAuthenticateIn).
                        when().
                        post("/rest/authentications").
                        then().
                        statusCode(HttpStatus.OK.value()).extract().response().as(new TypeToken<ApiBaseResponse<AuthenticationOut>>() {
                }.getRawType());

        System.out.println(authenticationOut);
    }

    @Test
    public void testStandardAuthenticationValidationErrorExpected() throws Exception {
        StandardAuthenticationIn standardAuthenticateIn = new StandardAuthenticationIn();
        standardAuthenticateIn.setPassword(null);
        standardAuthenticateIn.setLogin(null);
        Object authenticationOut =
                given().
                        contentType(ContentType.JSON).
                        body(standardAuthenticateIn).
                        when().
                        post("/rest/authentications").
                        then().
                        statusCode(HttpStatus.BAD_REQUEST.value()).extract().response().as(new TypeToken<ApiBaseResponse<AuthenticationOut>>() {
                }.getRawType());

        System.out.println(authenticationOut);

    }
}
