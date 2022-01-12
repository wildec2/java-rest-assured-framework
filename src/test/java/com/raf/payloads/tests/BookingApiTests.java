package com.raf.payloads.tests;

import com.raf.requests.BookingApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class BookingApiTests {

    @Test(description = "sample test")
    public void getBookingShouldReturn200(){
        Response response = BookingApi.getBookings();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }

}