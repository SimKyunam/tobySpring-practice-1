import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="/applicationContext.xml")
class UserDaoTest {
    @Autowired
    private ApplicationContext context;

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){
        this.dao = context.getBean("userDao", UserDao.class);

        user1 = new User("gyumee", "박성철", "springno1");
        user2 = new User("leegw700", "이길원", "springno2");
        user3 = new User("bumjin", "박범진", "springno3");
    }

    @Test
    public void addAndGet() throws SQLException {
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
        dao.deleteAll();
        assertThat(dao.getCount(), equalTo(0));

        dao.add(user1);
        assertThat(dao.getCount(), equalTo(1));

        dao.add(user2);
        assertThat(dao.getCount(), equalTo(2));

        dao.add(user3);
        assertThat(dao.getCount(), equalTo(3));
    }

    @Test
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), equalTo(0));

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unkwon_id");
        });
    }
}