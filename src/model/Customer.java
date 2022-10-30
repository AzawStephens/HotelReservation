package model;
import java.util.Objects;
import java.util.regex.Pattern;
public class Customer {
    String firstName;
    String lastName;
    public String email;
    Pattern pattern = Pattern.compile("^(.+)@(.+).com$");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString()
    {
        return "First name: "+firstName+" Last name: "+lastName+" email: "+email;
    }
    public Customer( String theEmail, String first, String last)
    {
        this.email = theEmail;
            if(!pattern.matcher(theEmail).matches())
            {
                throw new IllegalArgumentException("Invalid email address");
            }
        this.firstName = first;
        this.lastName = last;
    }
    public String getEmail()
    {
        return email;
    }
}
