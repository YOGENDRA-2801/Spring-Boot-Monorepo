package com.yogendrayadav.codingshuttle.Introduction.NotificationServiceImplementation;

import com.yogendrayadav.codingshuttle.Introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
//@Qualifier("email")
@Component
//@ConditionalOnProperty(name = "notification.type", havingValue = "email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("From Email : " + message);
    }
}
