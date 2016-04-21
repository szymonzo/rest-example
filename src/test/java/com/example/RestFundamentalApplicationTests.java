package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestFundamentalApplication.class)
@WebAppConfiguration
public class RestFundamentalApplicationTests {


    @Value("${local.server.port}")
    private int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
    }


}
