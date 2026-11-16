public class Room {
    int roomId;
    int roomNumber;
    String category;
    double pricePerNight;
    int capacity;
    String description;
    boolean active;

    public Room(int roomId, int roomNumber, String category, double pricePerNight, int capacity, String description) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.description = description;
        this.active = true;
    }

    public double calculatePrice(double daysSpent) {
        return daysSpent * this.pricePerNight;
    }
}


