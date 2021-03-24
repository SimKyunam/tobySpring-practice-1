package learningtest.spring.factorybean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by mileNote on 2021-03-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="/FactoryBeanTest-context.xml")
class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean(){
        Object message = context.getBean("message");
        assertThat(message.getClass(), is(Message.class));
        assertThat(((Message)message).getText(), is("Factory Bean"));
    }

    @Test
    public void getFactoryBean() throws Exception{
        Object factory = context.getBean("&message");
        assertThat(factory.getClass(), is(MessageFactoryBean.class));
    }
}