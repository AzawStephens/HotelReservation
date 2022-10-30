package service;
import model.Customer;
import java.util.*;
public class CustomerService {
    private CustomerService() {} //Stops objects from being able to be created
    static CustomerService customerService = new CustomerService();
    public static CustomerService getInstance(){
        return customerService;
    }
    public Collection<Customer> customerSet = new HashSet<Customer>();
    public Customer customer;
    public void addCustomer(String email, String first, String last) {
        customer = new Customer(email,first,last);
        customerSet.add(customer);
    }
    public Customer getCustomer(String customerEmail)
    {   for(Customer customer1: customerSet)
        {
            if(customer1.getEmail().equalsIgnoreCase(customerEmail))
            {
                return customer1;
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers(){
        return customerSet;
    }
}
