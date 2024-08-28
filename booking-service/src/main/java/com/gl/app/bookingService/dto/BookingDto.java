package com.gl.app.bookingService.dto;


import com.gl.app.bookingService.BookingServiceApplication;
import lombok.Data;

@Data
public class BookingDto {

    private String userId;
    private String bookingId;
    private String packageId;
    private String packageName;
    private String packageImage;
    private String bookingDate;
    private Integer bookingPerson;
    private Integer bookingRooms;

    BookingDto(){
        this.bookingId= BookingServiceApplication.getId();
    }
}
