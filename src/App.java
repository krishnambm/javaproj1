import java.util.ArrayList;
import java.util.Collections;
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

        Collections.sort(customerList, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        customerList.forEach((x) -> System.out.println(x.getName()));

        System.out.println("\n\n");
        List<Customer> fList = customerList.stream().filter(c1 -> c1.getName().startsWith("j")).collect(Collectors.toList());
        fList.forEach((x) -> System.out.println(x.getName()));
    }
}
