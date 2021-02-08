package user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Created by mileNote on 2021-01-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public UserDao(){

    };

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        //내부 클레스 사용
//        class AddStatement implements StatementStrategy{
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps =
//                        c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//
//                return ps;
//            }
//        }
//
//        StatementStrategy st = new AddStatement();
//        jdbcContextWithStatementStrategy(st);

        jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps =
                        c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });

    }

    public User get(String id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = dataSource.getConnection();
            ps = c.prepareStatement("select * from users where id = ? ");
            ps.setString(1, id);

            rs = ps.executeQuery();
            User user = null;
            if(rs.next()){
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            if(user == null) throw new EmptyResultDataAccessException(1);
            return user;
        }catch(SQLException e){
            throw e;
        }finally{
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                }
            }
            if (ps != null){
                try{
                    ps.close();
                }catch (SQLException e){
                }
            }
            if (c != null){
                try{
                    c.close();
                }catch (SQLException e){
                }
            }
        }
    }

    public void deleteAll() throws SQLException{
        //Try-With-Resources 사용 jdk1.7부터 사용 가능
//        try(Connection c = dataSource.getConnection();
//            PreparedStatement ps = c.prepareStatement("delete from users");
//        ){
//            ps.executeUpdate();
//        }catch(SQLException e) {
//            throw e;
//        }

//        StatementStrategy st = new DeleteAllStatement();
//        jdbcContextWithStatementStrategy(st);

        //익명 클레스
        jdbcContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement("delete from users");
            }
        });
    }

    public int getCount() throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = dataSource.getConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            throw e;
        }finally{
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                }
            }
            if (ps != null){
                try{
                    ps.close();
                }catch (SQLException e){
                }
            }
            if (c != null){
                try{
                    c.close();
                }catch (SQLException e){
                }
            }
        }
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        }catch(SQLException e) {
            throw e;
        }finally{
            if(ps != null){try {ps.close();} catch(SQLException e){}}
            if(c != null){try {c.close();} catch(SQLException e){}}
        }
    }
}