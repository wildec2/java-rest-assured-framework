package com.raf.requests;

import com.raf.payloads.requests.Booking;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BookingApi extends BaseApi {

    private final String PATH = "booking/";

    @Step("get booking request")
    public Response getBookings(){
        return requestSender.when()
            .get(PATH);
    }

    @Step("post booking request")
    public Response postBooking(Booking payload) {
        return requestSender.body(payload)
                .when()
                .post(PATH);
    }

    @Step("delete booking request")
    public Response deleteBooking(int id, String tokenValue) {
        return requestSender.header("Cookie", "token=" + tokenValue)
                .when()
                .delete(PATH + Integer.toString(id));
    }
}