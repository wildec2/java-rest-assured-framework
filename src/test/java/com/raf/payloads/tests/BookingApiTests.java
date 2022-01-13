package com.raf.payloads.tests;

import com.raf.payloads.requests.Auth;
import com.raf.payloads.requests.Booking;
import com.raf.payloads.requests.BookingDates;
import com.raf.payloads.responses.BookingResponse;
import com.raf.requests.AuthApi;
import com.raf.requests.BookingApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookingApiTests {

    @Test(description = "get bookings 200s")
    public void getBookingShouldReturn200(){
        Response response = BookingApi.getBookings();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }

    @Test(description = "post booking 201s")
    public void postBookingReturns201(){
        BookingDates dates = new BookingDates(LocalDate.of( 2022, 7, 1), LocalDate.of( 2022, 7, 4));
        Booking payload = new Booking( 1, "Colum", "Wilde", true, dates, "Breakfast");

        Response response = BookingApi.postBooking(payload);

        assertThat("Incorrect response code", response.getStatusCode(), is(201));
    }

    @Test(description = "delete booking 202s")
    public void deleteBookingReturns202() {
        BookingDates dates = new BookingDates(LocalDate.of( 2023, 8, 21), LocalDate.of( 2023, 8, 22));
        Booking payload = new Booking( 1, "Colum", "Wilde", true, dates, "Breakfast");

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth("admin", "password");
        Response authResponse = AuthApi.postAuth(auth);

        Response deleteResponse = BookingApi.deleteBooking(createdBookingResponse.getBookingid(), authResponse.getCookie("Token"));

        assertThat("Incorrect response code", deleteResponse.getStatusCode(), is(202));
    }
}