package user.sqlservice;

/**
 * Created by mileNote on 2021-05-09
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface SqlRegistry {
    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFoundException;
}
