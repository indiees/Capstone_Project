package model;

public class Booking {
    public int bay_id;
    public String location;
    public int max_capacity;
    public Booking(){

    }
    public Booking(int bay_id, String location, int max_capacity){
        this.bay_id = bay_id;
        this.location = location;
        this.max_capacity = max_capacity;
    }
    public Booking(String location, int max_capacity){
        this.location = location;
        this.max_capacity = max_capacity;
    }
}
