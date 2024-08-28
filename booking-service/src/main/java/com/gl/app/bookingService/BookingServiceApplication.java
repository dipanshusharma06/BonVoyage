package com.gl.app.bookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableFeignClients

public class BookingServiceApplication {
	@Autowired
	public static ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}
	private static final AtomicInteger COUNTER=new AtomicInteger(101);
	public static String getId() {
		return "B"+COUNTER.getAndIncrement();
	}
}
