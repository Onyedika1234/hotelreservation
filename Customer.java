public class Customer extends User{
    int customerId;
    String name;
    String email;

    public Customer(String name, String email, int customerId) {
        super(name, email);
        this.customerId = customerId;
    }

    String role() {
        return "CUSTOMER";
    }
    
}
