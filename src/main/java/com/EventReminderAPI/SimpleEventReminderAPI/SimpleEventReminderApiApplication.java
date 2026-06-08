package com.EventReminderAPI.SimpleEventReminderAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleEventReminderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleEventReminderApiApplication.class, args);
	}
	

}
