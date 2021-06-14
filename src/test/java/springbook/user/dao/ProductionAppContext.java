package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by mileNote on 2021-06-14
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@Configuration
@Profile("production")
public class ProductionAppContext {
    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        return mailSender;
    }
}
