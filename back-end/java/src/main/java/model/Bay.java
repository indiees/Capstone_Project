package model;

import java.sql.Timestamp;

public class Bay {
    public int bay_id;
    public String location;
    public int max_capacity;
    public Bay(){

    }
    public Bay(int bay_id, String location, int max_capacity){
        this.bay_id = bay_id;
        this.location = location;
        this.max_capacity = max_capacity;
    }
    public Bay(String location, int max_capacity){
        this.location = location;
        this.max_capacity = max_capacity;
    }

    public int getBay_id() {
        return bay_id;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Bay)){
            return false;
        }
        Bay b = (Bay) o;
        return ((b.bay_id == this.bay_id)
                && (b.location.equals(this.location))
                && (b.max_capacity == this.max_capacity));
    }

}
