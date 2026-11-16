public class Admin extends User{
    
    String name;
    String email;
    
    Admin(String name, String email) {
        super(name, email);
    }

    String role() {
        return "ADMIN";
    }
}
