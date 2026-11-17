import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        int arraySize = 0;

        Customer[] customers = new Customer[2000];
        Customer customer;
        int customerCount = 0;

        Room[] rooms = new Room[2000];
        int roomCount = 0;

        String role;
        int authOption;
        final String adminPassword = "onyedika3328";

        System.out.println("*****************************************");
        System.out.println("Welcome to Hotel Reservation Application");
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

        switch(role) {
            case "CUSTOMER" -> userLogics();
            case  "ADMIN" -> adminLogics(arraySize, rooms, roomCount,customers, customerCount);
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

   static public void adminLogics(int arraySize, Room[] rooms, int roomCount,Customer[] customers, int customerCount) {
        boolean isRunning = true;
        int adminOption = 0;
        while (isRunning) {
        adminOption = displayAdminDashboard();

        switch (adminOption) {
        case 1 -> roomCount = addNewRoom(rooms, roomCount);
        case 2 -> viewRooms(rooms, roomCount);
        case 3-> rooms = updateRoom(rooms, roomCount);
        case 4-> System.out.println("remove rooms");
        case 5 -> viewCustomers(customers, customerCount);
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

    static public void viewRooms(Room[] rooms, int roomCount) {
        if(roomCount <= 0) {
            System.out.println("There are no rooms available.");
            return;
        }

        System.out.println("Rooms");

        for(int i = 0; i < roomCount; i++) {
            System.out.println("\nRoom ID: " + rooms[i].getId());
            System.out.println("Room Number: " + rooms[i].getroomNumber());
            System.out.println("Category: " + rooms[i].getCategory());
            System.out.println("Description: " + rooms[i].getDescription());
            System.out.println("Price: " + rooms[i].getPricePerNight());
            System.out.println("Capacity: " + rooms[i].getCapacity());
            System.out.println(rooms[i].active ? "ACTIVE" : "INACTIVE");
        }
    }

    static public void viewCustomers(Customer[] customers, int customerCount) {
        for(int i =0; i < customerCount; i++) {
            System.out.println("\n");
            System.out.println(customers[i].name);
            System.out.println(customers[i].email);
            System.out.println("\n");
        }
    }

    static public Room[] updateRoom(Room[] rooms, int roomCount) {
        System.out.print("Input the id of the room you would like to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Which field would you like to change");
        System.out.println("1. Category");
        System.out.println("2. Price");
        System.out.println("3. Capacity");
        System.out.println("4. Description");
        System.out.print("Choose an option: ");
        int updateOption = scanner.nextInt();
        scanner.nextLine();

        if(updateOption < 1 || updateOption > 4) {
            System.out.println("Invalid option.");
            return rooms;
        }

        for(int i = 0; i< roomCount; i++){
            if(rooms[i].getId() == id) {
                if(updateOption == 1) {
                    System.out.print("Enter the new category: ");
                    String category = scanner.nextLine();

                    rooms[i].setCategory(category);
                }else if(updateOption == 2) {
                    System.out.print("Enter the new price per night: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    rooms[i].setPricePerNight(price);
                }else if(updateOption == 3){
                    System.out.print("Enter the new capacity of the room: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();

                    rooms[i].setCapacity(capacity);
                }else {
                    System.out.print("Enter the new description: ");
                    String description = scanner.nextLine();

                    rooms[i].setDescription(description);
                }

                break;
            }
        }

        return rooms;
    }
}

