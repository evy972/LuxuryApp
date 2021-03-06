package com.example.evy.ocs;

/**
 * User is represents the user of the application
 */

public class User {
    public String username;
    public String password;
    public String Name;
    public String Email;
    public String City;
    public String UID;

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String uid) {
        this.UID = uid;
    }

    public User(String username, String password, String name, String city, String email) {
        this.username = username;
        this.password = password;
        this.Name = name;
        this.City = city;
        this.Email = email;
    }

    public User(String UID, String username, String password, String name, String city, String email) {
        this.UID = UID;
        this.username = username;
        this.Name = name;
        this.password = password;
        this.City = city;
        this.Email = email;
    }
}

