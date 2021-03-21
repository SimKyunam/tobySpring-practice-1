package learningtest.spring.factorybean;

/**
 * Created by mileNote on 2021-03-21
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;
    Class<? extends T> getObjectType();
    boolean isSingleton();
}
