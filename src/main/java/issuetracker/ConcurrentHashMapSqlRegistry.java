package issuetracker;

import issuetracker.sqlservice.SqlUpdateFailureException;
import issuetracker.sqlservice.UpdatableSqlRegistry;
import user.sqlservice.SqlNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mileNote on 2021-05-29
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry {
    private Map<String, String> sqlMap = new ConcurrentHashMap<String, String>();

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if (sql == null){
            throw new SqlNotFoundException(key +
                    "에 대한 SQL을 찾을 수 없습니다.");
        }else{
            return sql;
        }
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {
        if(sqlMap.get(key) == null){
            throw new SqlUpdateFailureException(key +
                    "에 해당하는 SQL을 찾을 수 없습니다.");
        }
        sqlMap.put(key, sql);
    }

    @Override
    public void updateSql(Map<String, String> sqlMap) throws SqlUpdateFailureException {
        for(Map.Entry<String, String> entry : sqlMap.entrySet()){
            updateSql(entry.getKey(), entry.getValue());
        }
    }
}
