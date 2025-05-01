package com.example.expense_tracker;

import com.example.expense_tracker.model.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpenseData {
    private static final List<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense expense) {
        if (expense.getId() == null) {
            expense.setId(UUID.randomUUID().toString());
        }
        expenses.add(0, expense); // Add to top
    }

    public static Expense getLatestExpense() {
        return expenses.isEmpty() ? null : expenses.get(0);
    }

    public static List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    public static Expense getExpenseById(String expenseId) {
        for (Expense expense : expenses) {
            if (expense.getId().equals(expenseId)) {
                return expense;
            }
        }
        return null;
    }

    public static List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public static List<Expense> getMoreExpenses() {
        List<Expense> moreExpenses = new ArrayList<>();
        for (int i = 0; i < 5 && i < expenses.size(); i++) {
            moreExpenses.add(expenses.get(i));
        }
        return moreExpenses;
    }
}