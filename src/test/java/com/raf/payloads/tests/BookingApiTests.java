package com.raf.payloads.tests;

import com.raf.payloads.requests.Booking;
import com.raf.payloads.requests.BookingDates;
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
        BookingDates dates = new BookingDates(LocalDate.of( 2022, 3, 1), LocalDate.of( 2022, 3, 4));
        Booking payload = new Booking( 1, "Colum", "Wilde", true, dates, "Breakfast");

        Response response = BookingApi.postBooking(payload);

        assertThat("Incorrect response code", response.getStatusCode(), is(201));
    }

}