import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws SQLException {
        Connection c = connectionMaker.makeConnection();
    }

    public User get(String id) throws SQLException {
        Connection c = connectionMaker.makeConnection();
        return new User();
    }
}
