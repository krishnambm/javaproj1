package AnnotationExample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UnitTester {

    public List<String> run(Object target)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        Class<?> clazz = target.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        List<String> results = new ArrayList<>();

        boolean allGood = false;
        for (Method method : methods) {
            if (method.isAnnotationPresent(TestMethod.class)) {
                allGood = handleTestMethod(target, method);
                if (!allGood) {
                    results.add("TestMethod: " + method.getName() + " failed.");
                } else {
                    results.add("TestMethod: " + method.getName() + " passed.");
                }
            } else {
                results.add("TestMethod: " + method.getName() + " ignored.");
            }
        }
        return results;
    }
    public boolean handleTestMethod(Object target, Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        TestMethod tm = (TestMethod) method.getAnnotation(TestMethod.class);
        if (method.getParameterCount() > 0 ) {
            System.out.print("Parameter count check failed: " + method.getParameterCount());
            return false;
        }
        if (method.getReturnType() != boolean.class) {
            System.out.print("Return type check failed: " + method.getParameterCount());
            return false;
        }
        if ((boolean) method.invoke(target) == false) {
            System.out.print("test execution check failed: " + (boolean) method.invoke(target));
            return false;

        };
        return true;
    }
}
