package springbook.user.dao;

import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import user.dao.UserDao;
import user.dao.UserDaoJdbc;
import user.service.DummyMailSender;
import user.service.UserService;
import user.service.UserServiceImpl;
import springbook.user.service.UserServiceTest;
import user.sqlservice.OxmSqlService;
import user.sqlservice.SqlRegistry;
import user.sqlservice.SqlService;
import user.sqlservice.updatable.EmbeddedDbSqlRegistry;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;


/**
 * Created by mileNote on 2021-06-08
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "user.dao")
@Import({SqlServiceContext.class, TestAppContext.class, ProductionAppContext.class})
public class AppContext {

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}
