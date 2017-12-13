import java.util.List;

public class SomeApp {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

        Vehicle v1 = new Vehicle();
        v1.setModelName("Mustang");
        v1.setNumDoors(2);
        v1.setNumWheels(4);

        Vehicle v2 = new Vehicle();
        v2.setModelName("TronorA12345678");
        v1.setNumDoors(16);
        v2.setNumWheels(32);

        ObjectValidator validator = new ObjectValidator();
        List<String> results = validator.validate(v1);
        results.addAll(validator.validate(v2));

        System.out.println("Results:");
        for (String result : results) {
            System.out.println(result);
        }

    }
}