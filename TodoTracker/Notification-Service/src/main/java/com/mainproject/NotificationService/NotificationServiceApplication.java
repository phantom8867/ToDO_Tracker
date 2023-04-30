package com.mainproject.NotificationService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

//	@Bean
//	FirebaseMessaging firebaseMessaging() throws IOException {
//		GoogleCredentials googleCredentials = GoogleCredentials
//				.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
//		FirebaseOptions firebaseOptions = FirebaseOptions
//				.builder()
//				.setCredentials(googleCredentials)
//				.build();
//		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "YOUR APP NAME");
//		return FirebaseMessaging.getInstance(app);
//	}
}
