package com.gl.app.bookingService.utils;

import com.gl.app.bookingService.BookingServiceApplication;
import com.gl.app.bookingService.dto.BookingDto;
import com.gl.app.bookingService.entity.Booking;

public class Mapper {
    // It is a method that maps an booking object to an bookingDto object.
     public static BookingDto mapToBookingDto(Booking booking){
         return BookingServiceApplication.modelMapper().map(booking,BookingDto.class);
     }
    // It is a method that maps an BookingDto object to an Booking object.
     public static Booking mapToBooking(BookingDto bookingDto){
         return BookingServiceApplication.modelMapper().map(bookingDto,Booking.class);
     }
}
