package user.service;

import user.domain.User;

/**
 * Created by mileNote on 2021-03-16
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
