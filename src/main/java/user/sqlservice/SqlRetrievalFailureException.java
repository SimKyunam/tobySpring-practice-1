package user.sqlservice;

/**
 * Created by mileNote on 2021-04-27
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class SqlRetrievalFailureException extends RuntimeException {
    public SqlRetrievalFailureException(String message) {
        super(message);
    }

    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }


}
