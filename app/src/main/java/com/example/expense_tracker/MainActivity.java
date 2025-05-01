package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expense_tracker.model.Expense;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView lastExpenseTextCombined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        lastExpenseTextCombined = findViewById(R.id.last_expense_text_combined);

        // BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Find the Sign Out button
        Button btnSignOut = findViewById(R.id.btnSignOut);

        // Set OnClickListener for the Sign Out button
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out the user (Firebase example)
                FirebaseAuth.getInstance().signOut();

                // Clear user session (if using SharedPreferences)
                getSharedPreferences("UserSession", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();

                // Redirect to WelcomeActivity
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.nav_home) {
                        return true; // already here
                    } else if (itemId == R.id.nav_add_expense) {
                        startActivity(new Intent(MainActivity.this, ExpenseTrackerActivity.class));
                        return true;
                    } else if (itemId == R.id.nav_expense_list) {
                        startActivity(new Intent(MainActivity.this, ItemExpenseActivity.class));
                        return true;
                    } else {
                        return false;
                    }
                }
            };

    @Override
    protected void onResume() {
        super.onResume();
        refreshLatestExpense();
    }

    private void refreshLatestExpense() {
        Expense latestExpense = ExpenseData.getLatestExpense();
        if (latestExpense != null) {
            lastExpenseTextCombined.setText(
                    String.format("%.2f %s", latestExpense.getAmount(), latestExpense.getCurrency()));
        } else {
            lastExpenseTextCombined.setText("0.00 USD");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnSignOut) {
            // Handle sign out
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}