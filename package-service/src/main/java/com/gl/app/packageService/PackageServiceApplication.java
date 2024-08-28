package com.gl.app.packageService;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class PackageServiceApplication {


	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(PackageServiceApplication.class, args);
	}

	private  static final AtomicInteger COUNTER = new AtomicInteger(101);
	public static String genId(){
		return "P"+ COUNTER.getAndIncrement();
	}

}