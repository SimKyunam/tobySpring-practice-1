package user.service;

import user.domain.User;

import java.util.List;

/**
 * Created by mileNote on 2021-03-16
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface UserService {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    void update(User user);
    void upgradeLevels();
}
