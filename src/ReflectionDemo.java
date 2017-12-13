import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ReflectionDemo {
    public static void main(String[] args) {
        ReflectionDemo rd = new ReflectionDemo();
        rd.messWithFields();
    }

    public void messWithFields() {
        String className = "SomeClass";
        try {
            Class<?> klass = Class.forName(className);
            Constructor<?> ktor = klass.getConstructor(int.class);
            Object obj = ktor.newInstance(10);
            Method mthd = klass.getMethod("getSquare", int.class);
            Object result = mthd.invoke(obj, 10);

            System.out.println("Square of 10: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SomeClass {
    public int j;
    private int i;

    SomeClass(int i) {
        this.i = i;
    }

    public int getSquare(int k) {
        return k * k;
    }
}
