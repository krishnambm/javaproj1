import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String name = "joe";
        String statusStr = "NORMAL";

        Status status = Status.valueOf(statusStr);
        int code = status.getCode();

        CustomerService service = new CustomerService();
        service.addCustomer("joe", status);
        service.addCustomer("john", status);
        service.addCustomer("abcd", status);
        service.addCustomer("david", status);

        ArrayList<Customer>  customerList = service.getCustomerList();

        // Sort students by Name
        Collections.sort(customerList, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        customerList.forEach((x) -> System.out.println("id=" + x.getId() + " name=" + x.getName()));
        System.out.println("\n");

        customersStartingWithUsingLambda(customerList, "j").forEach((x) -> System.out.println("id=" + x.getId() + " name=" + x.getName()));
        System.out.println("\n");
        customersStartingWith(customerList, "j").forEach((x) -> System.out.println("id=" + x.getId() + " name=" + x.getName()));
        System.out.println("\n");

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

    public static List<Customer> customersStartingWithUsingLambda(List<Customer> orig, String prefix){
        return orig.stream().filter(c1 -> c1.getName().startsWith(prefix)).collect(Collectors.toList());
    }


}
