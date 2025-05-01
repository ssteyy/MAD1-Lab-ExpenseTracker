package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.example.expense_tracker.databinding.ItemExpenseActivityBinding;
import com.example.expense_tracker.model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ItemExpenseActivity extends AppCompatActivity {

    private ItemExpenseActivityBinding binding;
    private ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ItemExpenseActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();

        binding.backToHomepage.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void setupRecyclerView() {
        expenseAdapter = new ExpenseAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(expenseAdapter);

        // Set item click listener
        expenseAdapter.setOnItemClickListener(expense -> {
            Intent intent = new Intent(this, DetailExpense.class);
            intent.putExtra("amount", expense.getAmount());
            intent.putExtra("currency", expense.getCurrency());
            intent.putExtra("remark", expense.getRemark());
            startActivity(intent);
        });

        // Add scroll listener to load more expenses
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    loadMoreExpenses(); // Fetch next page
                }
            }
        });

        // Add swipe-to-delete functionality
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                expenseAdapter.removeExpense(position); // Remove from adapter
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recyclerView);
    }

    private void loadMoreExpenses() {
        // Example: Fetch more expenses from a data source
        List<Expense> moreExpenses = ExpenseData.getMoreExpenses(); // Replace with your actual data-fetching logic
        if (moreExpenses != null && !moreExpenses.isEmpty()) {
            expenseAdapter.addExpenses(moreExpenses); // Add new data to the adapter
        } else {
            Toast.makeText(this, "No more expenses to load", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshData() {
        List<Expense> expenses = ExpenseData.getExpenses();
        expenseAdapter.updateData(expenses != null ? expenses : new ArrayList<>());
    }
}