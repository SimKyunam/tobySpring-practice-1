package learningtest.template;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mileNote on 2021-02-13
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(this.numFilepath), is(10));
    }

    @Test
    public void multiplyfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    @Test
    public void concatenateString() throws IOException{
        assertThat(calculator.concatenate(this.numFilepath), is("1234"));
    }
}