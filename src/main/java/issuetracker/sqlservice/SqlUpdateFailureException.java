package issuetracker.sqlservice;

/**
 * Created by mileNote on 2021-05-09
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class SqlUpdateFailureException extends RuntimeException{
    public SqlUpdateFailureException(String message) {
        super(message);
    }

    public SqlUpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
