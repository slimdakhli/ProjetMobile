package com.example.miniproject.Model;


public class User {
    private String Name;
    private String Password;

    public User(){

    }

    public User(String name, String password){
        Password = password;
        Name =name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
