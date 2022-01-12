package com.raf.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi {

    private static final String apiUrl = "https://automationintesting.online/booking/";

    public static Response getBookings(){
        return given().get(apiUrl); }

}
