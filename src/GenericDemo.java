import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/*
       Object
         ^
         |
      Number
      ^    ^
      |    |
  Integer  Double
 */

public class GenericDemo {
    public static void main(String[] args) {
        GenericDemo gc  = new GenericDemo();
        gc.go();
    }

    public void go() {

        List<Number> listN = new ArrayList<>();
        List<Integer> listI = new ArrayList<>();
        double result;

        listN.add(1);
        listN.add(2.3);

        result = sum(listN);
        System.out.println(result);

        listI.add(1);
        listI.add(12);
        listI.add(5);

        result = sum(listI);
        System.out.println(result);

        Integer [] nArr = {1, 2, 3};
        Double [] dArr = {1.1, 2.2, 3.3};

        addTo(listN, nArr);
        addTo(listN, dArr);
        System.out.println(listN);

        List l = listN;
        // Avoid Adding strings to integer lists
        // l.add("abc");

        for(Number it: listN) {
            System.out.println(it.doubleValue());
        }

        listN.forEach(i -> System.out.println(i));

        Integer i = 10;
        printO(i);
        printN(i);
        printI(i);

        // Using method references
        listI.forEach(this::printO);
        listI.forEach(GenericDemo::printN);
        listI.forEach(GenericDemo::printI);

        ourForEach(listI, this::printO);
        ourForEach(listI, GenericDemo::printN);
        ourForEach(listI, GenericDemo::printI);
    }

    public <T> void ourForEach(List<T> list, Consumer<T> consumer) {
        for(T t: list) {
            consumer.accept(t);
        }
    }

    public void printO(Object o) {
        System.out.println(o);
    }

    public static void printN(Number o) {
        System.out.println(o);
    }

    public static void printI(Integer o) {
        System.out.println(o);
    }

    public static void printD(Double o) {
        System.out.println(o);
    }

    // public double sum(List<Number> list) {
    public double sum(List<? extends Number> list) {
        double sum = 0;
        // list.add(1); Cannot add to list once it extends from Number instead of List<Number>
        // list.add(1.23);
        for(Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Add Integer list to Integer List
    // public void addTo(List<Integer> list, Integer[] arr) {

    // Add Double list to Double List
    // public void addTo(List<Double> list, Double[] arr) {

    // Add Integers to an Integer/super of Integer list
    // public void addTo(List<? super Integer> list, Integer[] arr) {

    // Add Doubles to an Double/super of Double list
    // public void addTo(List<? super Double> list, Double[] arr) {


    public <T> void addTo(List<? super T> list, T [] arr) {
        // T a = arr.get(0);  Can be extracted as arr is list of T only
        // T b = list.get(0); Cannot be done as list can be T or super of T
        for(T it : arr) {
            list.add(it);
        }
    }

}
