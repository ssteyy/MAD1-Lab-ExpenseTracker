package com.example.expense_tracker.model;

import java.util.UUID;

public class User {
    private String id;
    private String email;
    private String password;

    // Constructor, getters, and setters

    public User(String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
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
}
