package com.raf.requests;

import com.raf.payloads.requests.Auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {

    private static final String apiUrl = String.format("%sauth/", baseUrl);

    public static Response postAuth(Auth payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl + "login");
    }
}