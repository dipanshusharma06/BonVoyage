package com.gl.app.bookingService.service;


import com.gl.app.bookingService.client.PackageClient;
import com.gl.app.bookingService.dto.BookingDto;
import com.gl.app.bookingService.dto.PackageDto;
import com.gl.app.bookingService.entity.Booking;
import com.gl.app.bookingService.exception.ResourceNotFoundException;
import com.gl.app.bookingService.repository.BookingRepo;
import com.gl.app.bookingService.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepo bookingRepo;
    private final PackageClient packageClient;

    public BookingDto add(BookingDto bookingDto) {

        PackageDto packageDto=packageClient.getPackageById(bookingDto.getPackageId());
        bookingDto.setPackageName(packageDto.getPackageName());
        bookingDto.setPackageId(packageDto.getPackageID());
        bookingDto.setPackageImage(packageDto.getPackageImage());

        Booking booking = Mapper.mapToBooking(bookingDto);
        bookingRepo.save(booking);
        return Mapper.mapToBookingDto(booking);
    }

    public List<BookingDto> findAllUserBookings(String userId) {
        List<Booking> bookingList= bookingRepo.findAll();
        return bookingList.stream().
                filter((booking -> {
                    return booking.getUserId().equals(userId);
                })).map(Mapper::mapToBookingDto).toList();
    }

    public ResponseEntity<BookingDto> edit(String bookingId,BookingDto bookingDto) {
        Booking booking=bookingRepo.findById(bookingId).get();
        if (bookingDto.getBookingDate()!=null) booking.setBookingDate(bookingDto.getBookingDate());
        if(bookingDto.getBookingPerson()!=null) booking.setBookingPerson(bookingDto.getBookingPerson());
        if(bookingDto.getPackageId()!=null) booking.setPackageId(bookingDto.getPackageId());
        if (bookingDto.getPackageName()!=null) booking.setPackageName(bookingDto.getPackageName());
        if (bookingDto.getBookingRooms()!=null) booking.setBookingRooms(bookingDto.getBookingRooms());
        bookingRepo.save(booking);
        BookingDto bookingDto1=Mapper.mapToBookingDto(booking);
        return new ResponseEntity<>(bookingDto1,HttpStatus.OK);
    }

    public void delete(String bookingId) {
        Booking booking=bookingRepo.findById(bookingId).orElseThrow(()->
            new ResourceNotFoundException("Booking","bookingId",bookingId)
        );
        bookingRepo.delete(booking);
    }
}
