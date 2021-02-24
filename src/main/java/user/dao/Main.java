package user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDaoJdbc beanDao1 = context.getBean("userDao", UserDaoJdbc.class);
        UserDaoJdbc beanDao2 = context.getBean("userDao", UserDaoJdbc.class);

        System.out.println(beanDao1);
        System.out.println(beanDao2);

        DaoFactory factory = new DaoFactory();
        UserDaoJdbc dao1 = factory.userDao();
        UserDaoJdbc dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
    }
}
