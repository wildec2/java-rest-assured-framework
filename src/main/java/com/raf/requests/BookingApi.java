package com.raf.requests;

import com.raf.payloads.requests.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi extends BaseApi{

    private static final String apiUrl = String.format("%sbooking/", baseUrl);

    public static Response getBookings(){
        return given()
                .get(apiUrl);
    }

    public static Response postBooking(Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl);
    }

    public static Response deleteBooking(int id, String tokenValue) {
        return given()
                .header("Cookie", "token=" + tokenValue)
                .delete(apiUrl + Integer.toString(id));
    }
}