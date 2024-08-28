package com.gl.app.bookingService.repository;

import com.gl.app.bookingService.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,String> {
    Booking findByUserId(String userId);
}
