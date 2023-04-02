package com.example.bumblebeebackend.model;

import javax.persistence.*;

@Entity
public class Admin {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", initialValue = 0, allocationSize = 1)


    @Id
    private Long adminId;

    private String firstname;

    private String lastName;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    private String email;

    private String password;
}


