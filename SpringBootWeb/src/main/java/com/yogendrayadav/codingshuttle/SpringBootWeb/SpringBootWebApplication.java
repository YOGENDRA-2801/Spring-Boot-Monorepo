package com.yogendrayadav.codingshuttle.SpringBootWeb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootWebApplication implements CommandLineRunner
{
	static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Walk kadam = (n) -> {
//			System.out.println("Walked " + n + " steps");
//			return n;
//		} ;
//		kadam.walked(5);
//		Walk chaal = steps -> steps*2 ;
//		chaal.walked(4) ;

//		List<String> phal = List.of("Banana", "Dragon Fruit" , "Mango" , "Apple") ;
//		Stream<String> bahaav = phal.stream();
//		bahaav
//				.map( fruit -> fruit.length() )
//				.filter( fruit -> fruit > 5 )
//				.sorted()
//					.forEach( fruit -> System.out.println(fruit) );

//		List<String> phal = List.of("Banana", "Dragon Fruit" , "Mango" , "Apple") ;
//		List<Integer> fruitList = phal.stream().map( String :: length ).collect(Collectors.toList()) ;
//		System.out.println(fruitList);

	}
}

//interface Walk {
//	int walked (int n) ;
//}