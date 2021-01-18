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
        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");

        dao.deleteAll();
        assertThat(dao.getCount(), equalTo(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), equalTo(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), equalTo(user1.getName()));
        assertThat(userget1.getPassword(), equalTo(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), equalTo(user2.getName()));
        assertThat(userget2.getPassword(), equalTo(user2.getPassword()));
    }

    @Test
    public void count() throws SQLException{
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

        dao.deleteAll();
        assertThat(dao.getCount(), equalTo(0));

        dao.add(user1);
        assertThat(dao.getCount(), equalTo(1));

        dao.add(user2);
        assertThat(dao.getCount(), equalTo(2));

        dao.add(user3);
        assertThat(dao.getCount(), equalTo(3));
    }
}