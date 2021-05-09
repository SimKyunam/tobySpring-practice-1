package user.sqlservice;

/**
 * Created by mileNote on 2021-05-09
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class SqlNotFoundException extends RuntimeException{
    public SqlNotFoundException(String message) {
        super(message);
    }

    public SqlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
