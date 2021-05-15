package user.sqlservice;

/**
 * Created by mileNote on 2021-05-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class DefaultSqlService extends BaseSqlService{
    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
