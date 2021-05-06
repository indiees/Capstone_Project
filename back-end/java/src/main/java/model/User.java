package model;

import controller.util.Status;
import dao.BookingDAO;
import dao.CarDAO;

import java.sql.Timestamp;

public class User {
    private int user_id, account_type;
    private String email, password, first_name, last_name;
    public User(){

    }
    public User(int user_id, String email, String password, String first_name, String last_name, int account_type){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_type = account_type;
    }
    public User(String email, String password, String first_name, String last_name, int account_type){
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_type = account_type;
    }
    public String getEmail(){
        return this.email;
    }
    public String getFirst_name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public int getAccount_type(){return this.account_type;}
    public void setUser_id(int user_id) { this.user_id = user_id; }
    // Careful using the getter, make sure the user actually has an ID
    public int getUser_id(){
       return this.user_id;
    }
    @Override
    public String toString(){
        return this.first_name + " " + this.last_name + " Owned by " + this.email;
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }
        User u = (User) o;
        return ((u.user_id == this.user_id)
                && (u.email.equals(this.email))
                && (u.password.equals(this.password))
                && (u.first_name.equals(this.first_name))
                && (u.last_name.equals(this.last_name))
                && (u.account_type == this.account_type));
    }

    public Booking createBooking(int car_id) {
        Car car = CarDAO.getCar(car_id);
        return BookingDAO.createBooking(car.getCar_id(), this.user_id, car.getBay(),0, new Timestamp(System.currentTimeMillis()),0, car.getCost());
    }
}
