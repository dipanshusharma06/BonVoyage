package com.gl.app.bookingService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    String userId;
    @Id
    String bookingId;
    String packageId;
    String packageName;
    String packageImage;
    String bookingDate;
    Integer bookingPerson;
    Integer bookingRooms;

}
