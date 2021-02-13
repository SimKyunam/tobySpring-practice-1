package learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mileNote on 2021-02-13
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
