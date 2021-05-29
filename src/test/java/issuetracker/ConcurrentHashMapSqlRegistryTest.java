package issuetracker;

import issuetracker.sqlservice.SqlUpdateFailureException;
import issuetracker.sqlservice.UpdatableSqlRegistry;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.sqlservice.SqlNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mileNote on 2021-05-29
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class ConcurrentHashMapSqlRegistryTest {
    UpdatableSqlRegistry sqlRegistry;

    @BeforeEach
    public void setUp(){
        sqlRegistry = new ConcurrentHashMapSqlRegistry();
        sqlRegistry.registerSql("KEY1", "SQL1");
        sqlRegistry.registerSql("KEY2", "SQL2");
        sqlRegistry.registerSql("KEY3", "SQL3");
    }

    @Test
    public void find(){
        checkFindResult("SQL1", "SQL2", "SQL3");
    }

    private void checkFindResult(String expected1, String expected2, String expected3) {
       assertEquals(sqlRegistry.findSql("KEY1"), expected1);
       assertEquals(sqlRegistry.findSql("KEY2"), expected2);
       assertEquals(sqlRegistry.findSql("KEY3"), expected3);
    }

    @Test
    public void unknownKey(){
        assertThrows(SqlNotFoundException.class, ()->{
           sqlRegistry.findSql("SQL9999!@#");
        });
    }

    @Test
    public void updateSingle(){
        sqlRegistry.updateSql("KEY2", "Modified2");
        checkFindResult("SQL1", "Modified2", "SQL3");
    }

    @Test
    public void updateMulti(){
        Map<String, String> sqlmap = new HashMap<String, String>();
        sqlmap.put("KEY1", "Modified1");
        sqlmap.put("KEY3", "Modified3");

        sqlRegistry.updateSql(sqlmap);
        checkFindResult("Modified1", "SQL2", "Modified3");
    }

    @Test
    public void updateWithNotExistingKey(){
        assertThrows(SqlUpdateFailureException.class, ()->{
           sqlRegistry.updateSql("SQL9999!@#", "Modified2");
        });
    }
}