package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.expense_tracker.databinding.ActivityStoreDataBinding;
import com.example.expense_tracker.model.Expense;

public class DetailExpense extends AppCompatActivity {

    private ActivityStoreDataBinding binding;
    private TextView displayAmount, displayRemark, displayCurrency, displayCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize TextViews
        displayAmount = findViewById(R.id.displayAmount);
        displayRemark = findViewById(R.id.displayRemark);
        displayCurrency = findViewById(R.id.displayCurrency);
        displayCategory = findViewById(R.id.displayCategory);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String amount = intent.getStringExtra("amount");
        String remark = intent.getStringExtra("remark");
        String currency = intent.getStringExtra("currency");
        String category = intent.getStringExtra("category");

        // Set data to TextViews
        displayAmount.setText("Amount: " + amount);
        displayRemark.setText("Remark: " + remark);
        displayCurrency.setText("Currency: " + currency);
        displayCategory.setText("Category: " + category);

        // Get expense ID from Intent
        String expenseId = getIntent().getStringExtra("EXPENSE_ID");
        Expense expense = ExpenseData.getExpenseById(expenseId);

        if (expense != null) {
            // Populate fields with expense data
            binding.displayAmount.setText("Amount: " + expense.getAmount());
            binding.displayCurrency.setText(expense.getCurrency() != null ?
                    "Currency: " + expense.getCurrency() : "");
            binding.displayDate.setText(expense.getDate() != null ?
                    "Date: " + expense.getDate() : "");
            binding.displayCategory.setText(expense.getCategory() != null ?
                    "Category: " + expense.getCategory() : "");
            binding.displayRemark.setText(expense.getNote() != null ?
                    "Remark: " + expense.getNote() : "");
        } else {
            // Handle case where expense is not found
            Toast.makeText(this, "Expense not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Handle back button click
        binding.backButton.setOnClickListener(v -> finish());
    }
}