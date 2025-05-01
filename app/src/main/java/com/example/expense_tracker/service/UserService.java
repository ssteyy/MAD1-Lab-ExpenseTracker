package com.example.expense_tracker.service;

import com.example.expense_tracker.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{

    private List<User> users = new ArrayList<User>();

    @Override
    public void register(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if(confirmPassword == null || confirmPassword.isEmpty()){
            throw new IllegalArgumentException("Confirm password cannot be empty");
        }

        users.add(new User(email, password));
    }

    @Override
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
