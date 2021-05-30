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
public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}