package com.cb007727.EEATimeTableManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EeaTimeTableManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EeaTimeTableManagerApplication.class, args);
	}

}
