package com.example.expense_tracker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StoreDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);

        TextView amountTextView = findViewById(R.id.displayAmount);
        TextView currencyTextView = findViewById(R.id.displayCurrency);
        TextView remarkTextView = findViewById(R.id.displayRemark);
        TextView categoryTextView = findViewById(R.id.displayRemark);
        TextView dateTextView = findViewById(R.id.displayDate);

        // Retrieve the data from the intent
        String amount = getIntent().getStringExtra("amount");
        String currency = getIntent().getStringExtra("currency");
        String remark = getIntent().getStringExtra("remark");
        String category = getIntent().getStringExtra("category");
        String date = getIntent().getStringExtra("date");

        // Display the data
        amountTextView.setText(amount);
        currencyTextView.setText(currency);
        remarkTextView.setText(remark);
        categoryTextView.setText(category);
        dateTextView.setText(date);
    }
}