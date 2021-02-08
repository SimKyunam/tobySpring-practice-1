package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mileNote on 2021-02-08
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
