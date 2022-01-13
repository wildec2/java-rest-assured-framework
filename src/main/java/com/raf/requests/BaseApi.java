package com.raf.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseApi {

    protected static final String BASE_URL = System.getProperty("baseUrl", "https://automationintesting.online/");

    static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();
}