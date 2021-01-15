import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassCastException, SQLException;
}
