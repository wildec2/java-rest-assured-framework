package com.raf.tests;

import com.raf.payloads.requests.Booking;
import com.raf.payloads.requests.BookingDates;
import com.raf.payloads.responses.BookingResponse;
import com.raf.requests.BookingApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookingApiTests extends BaseTest{

    @Test(description = "get bookings 200s")
    public void getBookingShouldReturn200(){
        Response response = BookingApi.getBookings();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }

    @Test(description = "post booking 201s")
    public void postBookingReturns201(){
        Booking payload = getBookingPayload(LocalDate.of(2023, 5, 7), LocalDate.of(2023, 5, 8));

        Response response = BookingApi.postBooking(payload);

        assertThat("Incorrect response code", response.getStatusCode(), is(201));
    }

    @Test(description = "delete booking 202s")
    public void deleteBookingReturns202() {
        Booking payload = getBookingPayload(LocalDate.of(2023, 4, 11), LocalDate.of(2023, 4, 14));

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(createdBookingResponse.getBookingid(), token);

        assertThat("Incorrect response code", deleteResponse.getStatusCode(), is(202));
    }

    private Booking getBookingPayload(LocalDate checkIn, LocalDate checkOut) {
        BookingDates dates = new BookingDates(LocalDate.of(checkIn.getYear(), checkIn.getMonthValue(), checkIn.getDayOfMonth()), LocalDate.of(checkOut.getYear(), checkOut.getMonthValue(), checkOut.getDayOfMonth()));
        return new Booking(1, "Colum", "Wilde", true, dates, "Breakfast");
    }
}