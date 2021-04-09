package model;

import java.sql.Timestamp;

public class Booking {
    public int booking_id, car_id, user_id, start_bay_id, end_bay_id, duration; //duration is in hours
    public double rate;
    public Timestamp date; //The starting date/time of the booking
    public Booking(){

    }
    public Booking(int booking_id, int car_id, int user_id, int start_bay_id, int end_bay_id, int duration, double rate, Timestamp date){
        this.booking_id = booking_id;
        this.car_id = car_id;
        this.user_id = user_id;
        this.start_bay_id = start_bay_id;
        this.end_bay_id = end_bay_id;
        this.duration = duration;
        this.rate = rate;
        this.date = date;
    }
    public Booking(int car_id, int user_id, int start_bay_id, int end_bay_id, int duration, double rate, Timestamp date){
        this.car_id = car_id;
        this.user_id = user_id;
        this.start_bay_id = start_bay_id;
        this.end_bay_id = end_bay_id;
        this.duration = duration;
        this.rate = rate;
        this.date = date;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public double getRate() {
        return rate;
    }

    public int getDuration() {
        return duration;
    }

    public int getEnd_bay_id() {
        return end_bay_id;
    }

    public int getStart_bay_id() {
        return start_bay_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Timestamp getDate() {
        return date;
    }
}
