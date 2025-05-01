package com.example.expense_tracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expense_tracker.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerView extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_expense_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize expense list and adapter
        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(expenseAdapter);

        // Load expenses (this is just an example, you should load your data here)
        loadExpenses();

        return view;
    }

    private void loadExpenses() {
        // Add sample data to the expense list
        expenseList.add(new Expense("1", "50", 12.0, "USD", "Groceries", "2023-10-01"));
        expenseList.add(new Expense("2", "20", 30.0, "USD", "Transport", "2023-10-02"));
        // Notify adapter about data changes
        expenseAdapter.notifyDataSetChanged();
    }
}