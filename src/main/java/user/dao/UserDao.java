package user.dao;

import java.util.List;

/**
 * Created by mileNote on 2021-02-24
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
