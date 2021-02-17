import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.*;

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
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import user.dao.DaoFactory;
import user.dao.User;
import user.dao.UserDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations="/test-applicationContext.xml")
class UserDaoTest {
//    @Autowired
//    private ApplicationContext context;

//    @Autowired
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){
        //this.dao = context.getBean("userDao", UserDao.class);
        dao = new UserDao();
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC", "root", "root", true);
        dao.setDataSource(dataSource);

        user1 = new User("gyumee", "박성철", "springno1");
        user2 = new User("leegw700", "이길원", "springno2");
        user3 = new User("bumjin", "박범진", "springno3");
    }

    @Test
//    @DirtiesContext //컨텍스트 설정이 다른 경우 사용 (클래스, 메소드 가능)
    public void addAndGet() {
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
    public void count(){
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
    public void getUserFailure() {
        dao.deleteAll();
        assertThat(dao.getCount(), equalTo(0));

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unkwon_id");
        });
    }

    @Test
    public void getAll() {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size(), is(0));

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user3, users3.get(0));
        checkSameUser(user1, users3.get(1));
        checkSameUser(user2, users3.get(2));
    }

    private void checkSameUser(User user1, User user2){
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
    }
}