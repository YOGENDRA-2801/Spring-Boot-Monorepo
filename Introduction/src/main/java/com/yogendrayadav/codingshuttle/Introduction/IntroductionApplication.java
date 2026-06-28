package com.yogendrayadav.codingshuttle.Introduction;

import com.yogendrayadav.codingshuttle.Introduction.homework.CakeBaker;
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
	private final CakeBaker cakeBaker ;

	public IntroductionApplication(CakeBaker cakeBaker) {
		this.cakeBaker = cakeBaker;
	}

	static void main(String[] args) {
		SpringApplication.run(IntroductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cakeBaker.bakeCake();
	}
}