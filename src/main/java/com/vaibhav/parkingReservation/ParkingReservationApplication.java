package com.vaibhav.parkingReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration
@SpringBootApplication
public class ParkingReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingReservationApplication.class, args);
	}

}
