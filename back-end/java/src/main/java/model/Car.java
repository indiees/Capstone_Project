package model;

public class Car {
    private int car_id, year, bay_id;
    private double cost;
    private String color, liscence_plate, make;
    public Car(){

    }
    public Car(double cost, String color, String liscence_plate, String make, int year, int bay_id){
        this.cost = cost;
        this.color = color;
        this.liscence_plate = liscence_plate;
        this.make = make;
        this.year = year;
        this.bay_id = bay_id;
    }

    public Car(int car_id, double cost, String color, String liscence_plate, String make, int year, int bay_id){
        this.bay_id = bay_id;
        this.car_id = car_id;
        this.cost = cost;
        this.color = color;
        this.liscence_plate = liscence_plate;
        this.make = make;
        this.year = year;
    }

    public double getCost() {
        return cost;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getLiscence_plate() {
        return liscence_plate;
    }

    public String getMake() {
        return make;
    }

    public int getBay() {
        return this.bay_id;
    }
}
