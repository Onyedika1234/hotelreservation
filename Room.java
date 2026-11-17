public class Room {
    private int roomId;
    private int roomNumber;
    private String category;
    private double pricePerNight;
    private int capacity;
    private String description;
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

    public int getId() {
        return this.roomId;
    }

    public int getroomNumber() {
        return this.roomNumber;
    }

    public String getCategory(){
        return this.category;
    }

    public double getPricePerNight() {
        return this.pricePerNight;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getDescription() {
        return  this.description;
    }

     public void setCategory(String category) {
        this.category = category;
    }

    public void setPricePerNight(double pricePerNight) {
        if(pricePerNight <= 0) {
            System.out.println("Price cannot be a negative value");
        }

        this.pricePerNight = pricePerNight;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setDescription(String description) {
        if(description.isBlank()) {
            System.out.println("Description cannot be empty");
        }

        this.description = description;
    }

    public void setActiveToTrue() {
        this.active = true;
    }

    public void setActiveToFalse() {
        this.active = false;
    }

    public double calculatePrice(double daysSpent) {
        return daysSpent * this.pricePerNight;
    }
}