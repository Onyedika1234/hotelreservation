import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        int arraySize = 0;

        Customer[] customers;
        Customer customer;
        int customerCount = 0;

        Room[] rooms = new Room[2000];
        int roomCount = 0;

        String role;
        int authOption;
        final String adminPassword = "onyedika3328";

        System.out.println("*****************************************");
        System.out.println("Welcome to Hotel Reservation Appplication");
        System.out.println("*****************************************");

        
        authOption = authOption();

        if(authOption  == 1) {
            // customerCount = registerCustomer(customerCount);
            Object[] customerDetails = registerCustomer(customerCount);
            customerCount = (int) customerDetails[0];
            customer = (Customer) customerDetails[1];

            role = customer.role();
        } else {
        int retryCount = 0;
        int maxRetry = 5;
        boolean passwordMatch = false;

        while (retryCount < maxRetry && !passwordMatch) {

            Object[] result = registerAdmin(adminPassword, retryCount);

            retryCount = (int) result[0];
            passwordMatch = (boolean) result[1];
        }
        if (!passwordMatch) {
            System.out.println("Trials exhausted! Access denied.");
            main(args);
        }
        role = "ADMIN";
        }

        // if(role.equals("ADMIN")) {
        // System.out.print("How many rooms would you like to manage: ");
        // arraySize = scanner.nextInt();
        // scanner.nextLine();

        // if(arraySize <= 10) {
        //     System.out.println("Users to be managed must be greated than 10");
        //     return;
        // }
        // rooms = new Room[arraySize];
        // }
        switch(role) {
            case "CUSTOMER" -> userLogics();
            case  "ADMIN" -> adminLogic(arraySize, rooms, roomCount);
            default -> System.out.println("Invalid role");
        }
        scanner.close();
    }
 
    static public int authOption() {
        System.out.println("1. Continue as Customer");
        System.out.println("2. Continue as Admin");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if(option < 0 || option > 2){
            System.out.println("Invalid option");
            return 0;
        } else {
            return option;
        }
    }

    static public Object[] registerCustomer(int customerCount) {
        Object[] customerDetails = new Object[2];
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        if(name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email cannot be empty");
            return registerCustomer(customerCount);
        }

        int id = customerCount + 1;

        Customer customer = new Customer(name, email, id);

        customerCount++;
        customerDetails[0] = customerCount;
        customerDetails[1] = customer;

        return customerDetails;
    }

    static Object[] registerAdmin(String adminPassword, int retryCount) {
    Object[] returns = new Object[2];

    System.out.print("Enter the admin password: ");
    String password = scanner.nextLine();

    if(password.isEmpty()) {
        System.out.println("Password cannot be empty");
        return registerAdmin(adminPassword, retryCount);
    }

    boolean passwordMatch = false;

    if (password.equals(adminPassword)) {

        passwordMatch = true;
        System.out.println("Admin password is correct");

    } else {

        retryCount++;
        System.out.println("Wrong password!");

    }

    returns[0] = retryCount;
    returns[1] = passwordMatch;

    return returns;
}

    static public void userLogics() {
        System.out.println("this is the parent of all the customer logic");
    }

   static public void adminLogic(int arraySize, Room[] rooms, int roomCount) {
        boolean isRunning = true;
        int adminOption = 0;
        while (isRunning) {
        adminOption = displayAdminDashboard();

        switch (adminOption) {
        case 1 -> roomCount = addNewRoom(rooms, roomCount);
        case 2 -> viewRooms(rooms, roomCount);
        case 3-> System.out.println("Update room information");
        case 4-> System.out.println("remove rooms");
        case 5 -> System.out.println("View all customers");
        case 6-> System.out.println("View all reservations");
        case 7 -> System.out.println("Search reservations");
        case 8 -> System.out.println("Manage cancellations");
        case 9 -> System.out.println("View payment records");
        case 10 -> System.out.println("Generate hotel reports");
        case 11 -> isRunning = false;
        
        default -> {
                System.out.println("Invalid option");
                break;
            }
        }
        
    }
    }

    static public int displayAdminDashboard(){
        System.out.println("*******************");
        System.out.println("Admin Dashboard");
        System.out.println("*******************");
        System.out.println("1. Add hotel rooms");
        System.out.println("2. View all rooms");
        System.out.println("3. Update room information");
        System.out.println("4. Remove rooms");
        System.out.println("5. View all customers");
        System.out.println("6. View all reservations");
        System.out.println("7. Search reservations");
        System.out.println("8. Manage cancellations");
        System.out.println("9. View payment records");
        System.out.println("10. Generate hotel reports");
        System.out.println("11. Exit application");

        System.out.print("Choose an option: ");
        int userOption = scanner.nextInt();
        scanner.nextLine();

        if(userOption < 1 || userOption > 11) {
            System.out.println("Invalid option");
            return 0;
        }

        return userOption;
    }

    static public int addNewRoom(Room[] rooms, int roomCount) {
        int id = roomCount + 1;
        int roomNo = roomCount + 1;
        String category;
        double price;
        int capacity;
        System.out.print("Enter the hotel category: ");
        category = scanner.nextLine();

        System.out.print("Enter the price per night: ");
        price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter the room capacity: ");
        capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter a brief description of the room: ");
        String description = scanner.nextLine();

        if(category.isBlank() || price <= 0 || capacity<=0 || description.isBlank()) {
            System.out.println("Enter a proper response to the questions.");
            return roomCount;
        }

        Room room = new Room(id, roomNo, category,price, capacity, description);

        rooms[roomCount] = room;

        System.out.println("Room created successfully");

        roomCount++;

        return roomCount;
    }

    static public void viewRooms(Room[] rooms, int roomCount){
        if(rooms.length == 0) {
            System.out.println("No rooms available.");
            return;
        }
        if(roomCount <= 0) {
            System.out.println("There is no room");
            return;
        }

        System.out.println("Rooms");

        for(int i = 0; i < roomCount; i++) {
            System.out.println("\n");
            System.out.println(rooms[i].roomNumber);
            System.out.println(rooms[i].description);
            System.out.println(rooms[i].pricePerNight);
            System.out.println(rooms[i].capacity);
            System.out.println(rooms[i].active ? "ACTIVE": "TAKEN");
            System.out.println("\n");
        }
    }

}

