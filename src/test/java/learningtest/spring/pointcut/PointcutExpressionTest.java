package learningtest.spring.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mileNote on 2021-04-01
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
class PointcutExpressionTest {
    @Test
    public void methodTest() throws NoSuchMethodException {
        System.out.println(Target.class.getMethod("minus", int.class, int.class));
    }

    @Test
    public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int " +
            "learningtest.spring.pointcut.Target.minus(int,int) " +
                "throws java.lang.RuntimeException)");
//        pointcut.setExpression("execution(* minus(int,int))");
//        pointcut.setExpression("execution(* minus(..))");
//        pointcut.setExpression("execution(* *(..))");

        assertEquals(pointcut.getClassFilter().matches(Target.class) &&
                pointcut.getMethodMatcher().matches(
                        Target.class.getMethod("minus", int.class, int.class), null), true);

        assertEquals(pointcut.getClassFilter().matches(Target.class) &&
                pointcut.getMethodMatcher().matches(
                        Target.class.getMethod("plus", int.class, int.class), null), false);

        assertEquals(pointcut.getClassFilter().matches(Bean.class) &&
                pointcut.getMethodMatcher().matches(
                        Target.class.getMethod("method"), null), false);
    }

    public void pointcutMatches(String expression, Boolean expected, Class<?> clazz,
                                String methodName, Class<?>... args) throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        assertEquals(pointcut.getClassFilter().matches(clazz)
            && pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null), expected);
    }

    public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception{
        pointcutMatches(expression, expected[0], Target.class, "hello");
        pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
        pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
        pointcutMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
        pointcutMatches(expression, expected[4], Target.class, "method");
        pointcutMatches(expression, expected[5], Bean.class, "method");
    }

    @Test
    public void pointcut() throws Exception{
        targetClassPointcutMatches("execution(* *(..))", true, true, true, true, true, true);
    }
}