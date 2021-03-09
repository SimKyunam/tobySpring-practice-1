package user.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * Created by mileNote on 2021-03-01
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class UserService {
    UserDao userDao;
    private DataSource dataSource;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void upgradeLevels() throws Exception{
        PlatformTransactionManager transactionManager =
                new DataSourceTransactionManager(dataSource);

        TransactionStatus status =
                transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            List<User> users = userDao.getAll();
            for(User user: users){
                if(canUpgradeLevel(user)){
                    upgradeLevel(user);
                }
            }
            transactionManager.commit(status);
        }catch(Exception e){
            transactionManager.rollback(status);
            throw e;
        }
    }

    public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GORD = 30;
    private boolean canUpgradeLevel(User user){
        Level currentLevel = user.getLevel();
        switch (currentLevel){
            case BASIC: return (user.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GORD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level: " +currentLevel);
        }
    }

    protected void upgradeLevel(User user){
        user.upgradeLevel();
        userDao.update(user);
    }

    public void add(User user) {
        if(user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}
