package learningtest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by mileNote on 2021-03-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class UppercaseHandler implements InvocationHandler {
    Hello target;

    public UppercaseHandler(Hello target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String ret = (String)method.invoke(target, args);
        return ret.toUpperCase();
    }
}
