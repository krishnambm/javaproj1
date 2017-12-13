package annotation_example;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UnitTestTrigger {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        TestMyService s1 = new TestMyService();
        UnitTester u1 = new UnitTester();
        List<String> strings = u1.run(s1);
        System.out.println(strings);
    }
}
