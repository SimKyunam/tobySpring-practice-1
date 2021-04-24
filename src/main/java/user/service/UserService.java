package user.service;

import org.springframework.transaction.annotation.Transactional;
import user.domain.User;

import java.util.List;

/**
 * Created by mileNote on 2021-03-16
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@Transactional
public interface UserService {
    void add(User user);
    void deleteAll();
    void update(User user);
    void upgradeLevels();

    @Transactional(readOnly = true)
    User get(String id);

    @Transactional(readOnly = true)
    List<User> getAll();
}
