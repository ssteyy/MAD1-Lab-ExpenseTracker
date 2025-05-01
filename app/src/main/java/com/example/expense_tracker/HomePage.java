package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expense_tracker.databinding.ActivityMainBinding;

public class HomePage extends AppCompatActivity {

    ActivityMainBinding binding;

    double Amount;
    String selectRadio = "Not selecting";
    String selectedItem = "";
    String textRemark = "";
    String selectDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Receiving data from the Expense activity
        Intent intent = getIntent();
        Amount = intent.getDoubleExtra("DataAmount", 0.0);  // Receiving as double
        selectRadio = intent.getStringExtra("DataCurrency");
        selectedItem = intent.getStringExtra("selectedItem");
        textRemark = intent.getStringExtra("DataRemark");
        selectDate = intent.getStringExtra("DataDate");

        // Displaying the currency and amount
        binding.currencyText.setText(selectRadio);  // Direct assignment, no need for null check
        binding.amountText.setText(String.valueOf(Amount));

        // Button click listeners
        binding.btnViewDetail.setOnClickListener(v -> btnViewDetailClick());
        binding.btnAddExpense.setOnClickListener(v -> btnAddExpense());
    }

    private void btnViewDetailClick() {
        Intent viewDetailIntent = new Intent(this, DetailExpense.class);
        viewDetailIntent.putExtra("DataRemark", textRemark);
        viewDetailIntent.putExtra("selectedItem", selectedItem);
        viewDetailIntent.putExtra("DataAmount", Amount);  // Passing double to DetailExpense
        viewDetailIntent.putExtra("DataCurrency", selectRadio);
        viewDetailIntent.putExtra("DataDate", selectDate);

        startActivity(viewDetailIntent);
    }

    private void btnAddExpense() {
        Intent btnAddExpenseIntent = new Intent(this, ExpenseActivity.class);
        startActivity(btnAddExpenseIntent);
    }
}