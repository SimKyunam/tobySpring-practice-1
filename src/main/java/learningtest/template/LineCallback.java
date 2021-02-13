package learningtest.template;

/**
 * Created by mileNote on 2021-02-13
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
