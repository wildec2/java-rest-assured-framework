package com.raf.payloads.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raf.payloads.requests.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookingResponse {

    @JsonProperty
    private int bookingid;
    @JsonProperty
    private Booking booking;

}