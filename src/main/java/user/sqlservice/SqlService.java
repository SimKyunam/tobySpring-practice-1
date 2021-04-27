package user.sqlservice;

/**
 * Created by mileNote on 2021-04-27
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
