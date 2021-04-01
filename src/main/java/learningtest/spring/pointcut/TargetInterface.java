package learningtest.spring.pointcut;

/**
 * Created by mileNote on 2021-04-01
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface TargetInterface {
    public void hello();
    public void hello(String a);
    public int minus(int a, int b) throws RuntimeException;
    public int plus(int a, int b);
}
