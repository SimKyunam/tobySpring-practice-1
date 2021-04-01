package learningtest.spring.pointcut;

/**
 * Created by mileNote on 2021-04-01
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class Target implements TargetInterface{
    @Override
    public void hello() {

    }

    @Override
    public void hello(String a) {

    }

    @Override
    public int minus(int a, int b) throws RuntimeException{
        return 0;
    }

    @Override
    public int plus(int a, int b) {
        return 0;
    }

    public void method() {

    }
}
