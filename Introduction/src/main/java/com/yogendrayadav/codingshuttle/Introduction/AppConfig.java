package com.yogendrayadav.codingshuttle.Introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
//    Has more priority than annotation based configuration hence can override configuration made there
    @Bean
    @Scope("prototype")
    PaymentService paymentService() {
//        More logic for configuration can be added hence more control over configuration
        return new PaymentService() ;
    }
}
