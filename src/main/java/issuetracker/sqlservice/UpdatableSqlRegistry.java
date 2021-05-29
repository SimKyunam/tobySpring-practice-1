package issuetracker.sqlservice;

import user.sqlservice.SqlRegistry;

import java.util.Map;

/**
 * Created by mileNote on 2021-05-29
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface UpdatableSqlRegistry extends SqlRegistry {
    public void updateSql(String key, String sql) throws SqlUpdateFailureException;

    public void updateSql(Map<String, String> sqlMap) throws SqlUpdateFailureException;
}
