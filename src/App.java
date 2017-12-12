import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        String name = "joe";
        String statusStr = "NORMAL";

        Status status = Status.valueOf(statusStr);
        int code = status.getCode();

        CustomerService service = new CustomerService();
        service.addCustomer("joe", Status.PRIVILEGED);
        service.addCustomer("john", Status.NORMAL);
        service.addCustomer("paul", Status.NORMAL);
        service.addCustomer("rob", Status.NORMAL);
        service.addCustomer("artev", Status.RESTRICTED);
        service.addCustomer("david", status);
        service.addCustomer("charlie", status);
        service.addCustomer("pinto", Status.RESTRICTED);
        service.addCustomer("manoj", status);

        ArrayList<Customer> customerList = service.getCustomerList();
        Representer repr = (x) -> "id=" + x.getId() + " name=" + x.getName();

        printItems(customerList, repr);

        // Sort students by Name
        Collections.sort(customerList, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        printItems(customerList, repr);

        customersStartingWithUsingLambda(customerList, "j").forEach((x) -> System.out.println("id=" + x.getId() + " name=" + x.getName()));
        System.out.println("\n");
        customersStartingWith(customerList, "j").forEach((x) -> System.out.println("id=" + x.getId() + " name=" + x.getName()));

        List<String> names = getNames(customerList);
        System.out.println(names);

        List<String> statuses = getStatuses(customerList);
        System.out.println(statuses);

        System.out.println(doTransform(customerList, new GetName() ));
        System.out.println(doTransform(customerList, new GetStatus() ));
        System.out.println(doTransform(customerList, (customer) -> customer.getName()));
        System.out.println(doTransform(customerList, (customer) -> customer.getStatus().toString()));

        System.out.println(doTransformGeneric(customerList, new GetNameGeneric() ));
        System.out.println(doTransformGeneric(customerList, new GetStatusGeneric() ));
        System.out.println(doTransformGeneric(customerList, (customer) -> customer.getName()));
        System.out.println(doTransformGeneric(customerList, (customer) -> customer.getStatus().toString()));

        System.out.println(customerList.stream().map((customer) -> customer.getName()).count());
        System.out.println(customerList.stream().map((customer) -> customer.getStatus().toString()).count());

        Supplier<String> sup = getSupplier("hello");
        System.out.println(sup.get());

        customerList = service.getCustomerList();
        names = customerList.stream()
                .filter((customer) -> customer.getStatus() == Status.NORMAL)
                .map(customer -> customer.getName()).collect(Collectors.toList());
        System.out.println(names);

        names = customerList.stream().unordered().parallel()
                .peek(s -> System.out.println(Thread.currentThread().getName() + " Peek1: " + s.getName()))
                .filter((customer) -> customer.getStatus() == Status.NORMAL)
                .peek(s -> System.out.println(Thread.currentThread().getName() + " Peek2: " + s.getName()))
                .map(customer -> customer.getName()).collect(Collectors.toList());
        System.out.println(names);

        Map<Status, List<Customer>> statusMap = customerList.stream().unordered().parallel()
                .collect(Collectors.groupingBy(customer -> customer.getStatus()));
        System.out.println(statusMap);

        Map<Status, Set<String>> statusSet = customerList.stream().unordered().parallel()
                .collect(Collectors.groupingBy(customer -> customer.getStatus(),
                         Collectors.mapping(customer -> customer.getName(), Collectors.toSet() )));
        System.out.println(statusSet);


    }

    public static Supplier<String> getSupplier(String str) {
        Supplier<String> s = () -> "Supplied " + str;
        return s;
    }


    public static class GetNameFunction implements Function<Customer, String> {
        public String apply(Customer s) {
            return s.getName();
        }
    }

    public static class GetStatusFunction implements Function<Customer, String> {
        public String apply(Customer s) {
            return s.getStatus().toString();
        }
    }

    public static <T, R> List<R> doTransformGeneric(List<T> list, TransformGeneric<T, R> transform) {
        List<R> result = new ArrayList<>();
        for (T customer: list) {
            result.add(transform.doIt(customer));
        }
        return result;
    }
    public static List<String> doTransform(List<Customer> list, Transform transform) {
        List<String> result = new ArrayList<>();
        for (Customer customer: list) {
            result.add(transform.doIt(customer));
        }
        return result;
    }

    public static List<String> getNames(List<Customer> list) {
        List<String>  result = new ArrayList<>();
        for (Customer customer : list) {
            result.add(customer.getName());
        }
        return result;
    }

    public static List<String> getStatuses(List<Customer> list) {
        List<String>  result = new ArrayList<>();
        for (Customer customer : list) {
            result.add(customer.getStatus().toString());
        }
        return result;
    }

    public interface TransformGeneric<T, R> {
        public R doIt(T c);
    }

    public static class GetNameGeneric implements TransformGeneric<Customer, String> {
        public String doIt(Customer c) {
            return c.getName();
        }
    }

    public static class GetStatusGeneric implements TransformGeneric<Customer, String> {
        public String doIt(Customer c) {
            return c.getStatus().toString();
        }
    }

    public interface Transform {
        public String doIt(Customer c);
    }

    public static class GetName implements Transform {
        public String doIt(Customer c) {
            return c.getName();
        }
    }

    public static class GetStatus implements Transform {
        public String doIt(Customer c) {
            return c.getStatus().toString();
        }
    }

    public static List<Customer> customersStartingWith(List<Customer> orig, String prefix){
        List<Customer> result = new ArrayList<>();
        for (Customer cust : orig) {
            if (cust.getName().startsWith(prefix)) {
                result.add(cust);
            }
        }
        return result;

    }

    public <A> List<A> filter(List<A> orig, Predicate<A> checker) {
        List<A> result = new ArrayList<>();
        orig.forEach(arg -> {
            if (checker.test(arg)) {
                result.add(arg);
            }
        });
        return result;
    }

    public static List<Customer> customersStartingWithUsingLambda(List<Customer> orig, String prefix){
        return orig.stream().filter(c1 -> c1.getName().startsWith(prefix)).collect(Collectors.toList());
    }

    public interface Checker {
        public boolean isAcceptable(Customer s);
    }

    public static void printItems(ArrayList<Customer> orig, Representer representer) {
        orig.forEach((x) -> System.out.println(representer.printItem(x)));
        System.out.println("\n");
    }

    public interface Representer {
        public String printItem(Customer s);
    }


}
