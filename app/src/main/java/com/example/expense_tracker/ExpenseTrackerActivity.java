package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expense_tracker.model.Expense;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ExpenseTrackerActivity extends AppCompatActivity {
    private EditText inputAmount, inputRemark;
    private RadioGroup currencyGroup;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        inputAmount = findViewById(R.id.input_amount);
        inputRemark = findViewById(R.id.input_remark);
        currencyGroup = findViewById(R.id.currency_group);
        categorySpinner = findViewById(R.id.categorySpinner);
        Button btnAddExpense = findViewById(R.id.btn_add_expense);

        btnAddExpense.setOnClickListener(v -> saveExpense());
    }

    private void saveExpense() {
        String amount = inputAmount.getText().toString().trim();
        String remark = inputRemark.getText().toString().trim();
        String currency = getSelectedCurrency();
        String category = categorySpinner.getSelectedItem().toString();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (amount.isEmpty() || currency.isEmpty() || remark.isEmpty()) {
            Toast.makeText(this, "Please enter amount, remark, and select currency", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the expense
        Expense newExpense = new Expense(UUID.randomUUID().toString(), remark, Double.parseDouble(amount), currency, category, currentDate);
        ExpenseData.addExpense(newExpense);

        // Show the "Expense Saved!" message
        Toast.makeText(this, "Expense Saved!", Toast.LENGTH_SHORT).show();

        // Navigate back to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String getSelectedCurrency() {
        int selectedId = currencyGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.usd_btn) {
            return "USD";
        } else if (selectedId == R.id.khr_btn) {
            return "KHR";
        }
        return "";
    }
}