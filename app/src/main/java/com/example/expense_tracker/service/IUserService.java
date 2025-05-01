package com.example.expense_tracker.service;

import com.example.expense_tracker.model.User;

import java.util.List;

public interface IUserService {

    void register(String email, String password, String confirmPassword);
    User login(String email, String password);
    List<User> getAllUsers();
}
