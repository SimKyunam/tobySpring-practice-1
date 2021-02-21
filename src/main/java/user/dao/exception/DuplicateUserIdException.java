package user.dao.exception;

/**
 * Created by mileNote on 2021-02-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException (Throwable cause){
        super(cause);
    }
}
