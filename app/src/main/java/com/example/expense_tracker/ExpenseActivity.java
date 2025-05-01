package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expense_tracker.model.Expense;

import java.util.UUID;

public class ExpenseActivity extends AppCompatActivity {

    private EditText amountEditText, descriptionEditText, dateEditText;
    private Spinner categorySpinner;
    private RadioGroup currencyGroup;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        amountEditText = findViewById(R.id.input_amount);
        descriptionEditText = findViewById(R.id.input_remark);
        categorySpinner = findViewById(R.id.categorySpinner);
        currencyGroup = findViewById(R.id.currency_group);
        saveButton = findViewById(R.id.btn_add_expense);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();
                String date = dateEditText.getText().toString();
                int selectedCurrencyId = currencyGroup.getCheckedRadioButtonId();
                RadioButton selectedCurrencyButton = findViewById(selectedCurrencyId);
                String currency = selectedCurrencyButton.getText().toString();

                Expense newExpense = new Expense(UUID.randomUUID().toString(), description, Double.parseDouble(amount), currency, category, date);
                ExpenseData.addExpense(newExpense);

                Intent intent = new Intent(ExpenseActivity.this, MainActivity.class);
                intent.putExtra("amount", amount);
                intent.putExtra("currency", currency);
                startActivity(intent);
                finish();
            }
        });
    }
}