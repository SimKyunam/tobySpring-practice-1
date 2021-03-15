package user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mileNote on 2021-03-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class MockMailSender implements MailSender {
    private List<String> requests = new ArrayList<String>();

    public List<String> getRequests(){
        return requests;
    }

    public void send(SimpleMailMessage mailMessage) throws MailException{
        requests.add(mailMessage.getTo()[0]);
    }

    public void send(SimpleMailMessage[] mailMessage) throws MailException{

    }
}
