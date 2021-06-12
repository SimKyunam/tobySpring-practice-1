package springbook.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import springbook.user.service.UserServiceTest;
import springbook.user.service.UserServiceTest.TestUserService;
import user.dao.UserDao;
import user.service.DummyMailSender;
import user.service.UserService;

/**
 * Created by mileNote on 2021-06-13
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@Configuration
public class TestAppContext {

    @Bean
    public UserService testUserService(){
        return new TestUserService();
    }

    @Bean
    public MailSender mailSender(){
        return new DummyMailSender();
    }
}
