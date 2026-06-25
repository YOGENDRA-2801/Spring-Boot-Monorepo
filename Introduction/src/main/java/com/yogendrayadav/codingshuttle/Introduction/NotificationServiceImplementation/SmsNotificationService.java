package com.yogendrayadav.codingshuttle.Introduction.NotificationServiceImplementation;

import com.yogendrayadav.codingshuttle.Introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
//@ConditionalOnProperty(name = "notification.type", havingValue = "sms")
@Component
//@Qualifier("sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("From SMS : " + message);
    }
}
