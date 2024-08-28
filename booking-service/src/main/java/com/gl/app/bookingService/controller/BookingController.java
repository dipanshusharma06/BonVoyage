package com.gl.app.bookingService.controller;

import com.gl.app.bookingService.dto.BookingDto;
import com.gl.app.bookingService.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<BookingDto> add(@RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(bookingService.add(bookingDto), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingDto>> findAllUserBookings(@PathVariable String userId){
        return ResponseEntity.ok(bookingService.findAllUserBookings(userId));
    }
    @PutMapping("editbooking/{bookingId}")
    public ResponseEntity<BookingDto> edit(@PathVariable String bookingId,@RequestBody BookingDto bookingDto){
        return bookingService.edit(bookingId,bookingDto);
    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> delete(@PathVariable String bookingId){
        bookingService.delete(bookingId);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
