package learningtest.proxy;

/**
 * Created by mileNote on 2021-03-20
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class HelloTarget implements Hello{
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

    @Override
    public String sayThankYou(String name) {
        return "Thank You " + name;
    }
}
