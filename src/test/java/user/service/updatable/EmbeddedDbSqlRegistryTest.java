package user.service.updatable;

import issuetracker.AbstractUpdatableSqlRegistryTest;
import user.sqlservice.updatable.EmbeddedDbSqlRegistry;
import issuetracker.sqlservice.UpdatableSqlRegistry;
import org.junit.jupiter.api.AfterEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

/**
 * Created by mileNote on 2021-06-07
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    EmbeddedDatabase db;

    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        db = new EmbeddedDatabaseBuilder()
                .setType(HSQL)
                .addScript("classpath:updatable/sqlRegistrySchema.sql")
                .build();

        EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
        embeddedDbSqlRegistry.setDataSource(db);

        return embeddedDbSqlRegistry;
    }

    @AfterEach
    public void tearDown(){
        db.shutdown();
    }
}
