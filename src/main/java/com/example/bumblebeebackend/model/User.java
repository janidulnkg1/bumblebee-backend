package com.example.bumblebeebackend.model;

import javax.persistence.*;

@Entity

public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long userid;

    private String firstName;

    public User(String mail, String password) {
    }

    public User() {

    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String lastName;

    private String email;

    private String password;

}



