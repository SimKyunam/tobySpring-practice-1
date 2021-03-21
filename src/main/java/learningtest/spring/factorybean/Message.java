package learningtest.spring.factorybean;

/**
 * Created by mileNote on 2021-03-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class Message {
    String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text){
        return new Message(text);
    }
}
