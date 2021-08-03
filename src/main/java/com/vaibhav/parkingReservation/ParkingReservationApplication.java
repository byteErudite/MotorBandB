package com.vaibhav.parkingReservation;

import com.vaibhav.parkingReservation.security.EntityAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class ParkingReservationApplication {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new EntityAuditing();
	}

	public static void main(String[] args) {
		SpringApplication.run(ParkingReservationApplication.class, args);
	}

}
