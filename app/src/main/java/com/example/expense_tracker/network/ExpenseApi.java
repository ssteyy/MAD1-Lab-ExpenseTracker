package com.example.expense_tracker.network;

import com.example.expense_tracker.model.Expense;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExpenseApi {
    @GET("/expenses")
    Call<List<Expense>> getAllExpenses();

    @POST("/expenses")
    Call<Expense> addExpense(@Body Expense expense);
}