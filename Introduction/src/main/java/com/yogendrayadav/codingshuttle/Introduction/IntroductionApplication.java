package com.yogendrayadav.codingshuttle.Introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class IntroductionApplication implements CommandLineRunner
{
//	@Autowired
//	NotificationService notificationService ; // Field Dependency Injection - Not Preferred

//	final NotificationService notificationService ; // final - Immutable field ; Constructor DI

//	public IntroductionApplication(NotificationService notificationService) {
//		this.notificationService = notificationService; // Constructor Dependency Injection - Preferred
//	}

// 	public IntroductionApplication(NotificationService notificationService) {
//		this.notificationService = notificationService; // Using @Primary
//	}

//	public IntroductionApplication(
//			@Qualifier("email") NotificationService notificationService) {
//		this.notificationService = notificationService; // Using @Qualifier
//	}

//	public IntroductionApplication(NotificationService notificationService) {
//		this.notificationService = notificationService; // Using Conditional
//	}

	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();

	static void main(String[] args) {
		SpringApplication.run(IntroductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		notificationService.send("HELLO WORLD");
		for (Map.Entry<String, NotificationService> notificationService: notificationServiceMap.entrySet()) {
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("HELLO DUNIYA");
		}
	}
}