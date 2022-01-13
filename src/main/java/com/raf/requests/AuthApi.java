package com.raf.requests;

import com.raf.payloads.requests.Auth;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {

    private static final String PATH = "auth/login";

    @Step("login request")
    public static Response postAuth(Auth payload) {
        return given(requestSpecification)
                .body(payload)
                .when()
                .post(PATH);
    }
}