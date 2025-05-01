package com.example.expense_tracker.model;

public class Expense {
    private String id;
    private String note;
    private double amount;
    private String currency;
    private String category;
    private String date;

    public Expense(String id, String note, double amount, String currency, String category, String date) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getRemark() {
        return note;
    }
}