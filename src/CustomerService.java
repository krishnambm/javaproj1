import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomerService {

    private static AtomicInteger nextId = new AtomicInteger(0);
    private HashMap<Integer, Customer> customerList = new HashMap<Integer, Customer>();

    public ArrayList<Customer> getCustomerList() {

        // Printing using lambda expression instead of using for loop
        customerList.forEach((k, v) -> System.out.println("id=" + k + " name=" + v.getName()));
        System.out.println("\n\n");

        Collection<Customer> custList = customerList.values();
        return new ArrayList(custList);

    }

    public Customer getCustomer(Integer id) {
        for (Integer key : customerList.keySet()) {
            Customer temp = customerList.get(key);
            if (temp.getId() == id) {
                Customer.CustomerBuilder cb = new Customer.CustomerBuilder();
                return cb.id(temp.getId()).name(temp.getName()).status(temp.getStatus()).build();
            }
        }
        ;
        return null;
    }

    public Customer addCustomer(String name, Status status) {
        // Customer cust = new Customer(nextId.incrementAndGet(), name, status);
        Customer.CustomerBuilder cb = new Customer.CustomerBuilder();

        Customer cust = cb.id(nextId.incrementAndGet()).name(name).status(status).build();
        customerList.put(cust.getId(), cust);
        return cust;
    }

}
