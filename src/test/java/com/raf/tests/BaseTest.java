package com.raf.tests;

import com.raf.payloads.requests.Auth;
import com.raf.requests.AuthApi;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected AuthApi authApi = new AuthApi();
    protected String token;

    @BeforeSuite
    public void dataSetUp() {
        token = getAdminToken();
    }
    
    public String getAdminToken() {
        Auth auth = new Auth("admin", "password");
        Response authResponse = authApi.postAuth(auth);
        return authResponse.getCookie("Token");
    }
}