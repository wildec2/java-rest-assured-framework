package com.raf.requests;

import com.raf.payloads.requests.Auth;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthApi extends BaseApi {

    private final String PATH = "auth/login";

    @Step("login request")
    public Response postAuth(Auth payload) {
        return requestSender.body(payload)
                .when()
                .post(PATH);
    }
}