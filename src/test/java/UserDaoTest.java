import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;
import user.dao.DaoFactory;
import user.dao.UserDao;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
class UserDaoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao beanDao1 = context.getBean("userDao", UserDao.class);
        UserDao beanDao2 = context.getBean("userDao", UserDao.class);
        System.out.println(beanDao1);
        System.out.println(beanDao2);
    }
}