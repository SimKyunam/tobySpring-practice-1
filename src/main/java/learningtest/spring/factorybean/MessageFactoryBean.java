package learningtest.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by mileNote on 2021-03-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class MessageFactoryBean implements FactoryBean<Message> {
    String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<? extends Message> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
