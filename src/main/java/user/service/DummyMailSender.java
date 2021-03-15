package user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by mileNote on 2021-03-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class DummyMailSender implements MailSender {
    public void send(SimpleMailMessage mailMessage) throws MailException{
    }

    public void send(SimpleMailMessage[] mailMessages) throws MailException{
    }
}
