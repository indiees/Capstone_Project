package model;

import controller.util.Status;

public class User {
    private int user_id;
    private String email, password, first_name, last_name;
    public User(){

    }
    public User(int user_id, String email, String password, String first_name, String last_name){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public User(String email, String password, String first_name, String last_name){
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
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
                && (u.email == this.email)
                && (u.password == this.password)
                && (u.first_name == this.first_name)
                && (u.last_name == this.last_name));
    }
}
