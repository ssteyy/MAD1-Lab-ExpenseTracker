package com.example.expense_tracker.service;

import android.app.Application;

public class MyApp extends Application {

    IUserService userService;

    @Override
    public void onCreate() {
        super.onCreate();
        userService = new UserService();
    }

    public IUserService getUserService(){
        return userService;
    }



}