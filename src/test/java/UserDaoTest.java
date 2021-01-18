import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;
import user.dao.DaoFactory;
import user.dao.User;
import user.dao.UserDao;

import java.sql.SQLException;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = new User();
        user.setId("gyumee");
        user.setName("박성철");
        user.setPassword("springno1");

        dao.add(user);

        User user2 = dao.get(user.getId());
        assertThat(user2.getName(), equalTo(user.getName()));
        assertThat(user2.getPassword(), equalTo(user.getPassword()));

    }
}