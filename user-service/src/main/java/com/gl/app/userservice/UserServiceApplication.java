package com.gl.app.userservice;

import com.gl.app.userservice.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {
	@Bean
	public Mapper mapper(){
		return new Mapper(new ModelMapper());
	}
	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
	}

}
