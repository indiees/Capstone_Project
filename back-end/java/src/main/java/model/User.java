package model;

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
    public int getUser_id(){
       return this.user_id;
    }
    @Override
    public String toString(){
        return this.first_name + " " + this.last_name + " Owned by " + this.email;
    }
}
