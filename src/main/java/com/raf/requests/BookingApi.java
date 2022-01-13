package com.raf.requests;

import com.raf.payloads.requests.Booking;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi extends BaseApi {

    private static final String PATH = "booking/";

    @Step("get booking request")
    public static Response getBookings(){
        return given(requestSpecification)
                .get(PATH);
    }

    @Step("post booking request")
    public static Response postBooking(Booking payload) {
        return given(requestSpecification)
                .body(payload)
                .when()
                .post(PATH);
    }

    @Step("delete booking request")
    public static Response deleteBooking(int id, String tokenValue) {
        return given(requestSpecification)
                .header("Cookie", "token=" + tokenValue)
                .delete(PATH + Integer.toString(id));
    }
}