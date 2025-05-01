package com.example.expense_tracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expense_tracker.databinding.ItemExpenseBinding;
import com.example.expense_tracker.model.Expense;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private final List<Expense> expenses;
    private OnItemClickListener onItemClickListener;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExpenseBinding binding = ItemExpenseBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ExpenseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void updateData(Object o) {
        if (o instanceof List) {
            expenses.clear();
            expenses.addAll((List<Expense>) o);
            notifyDataSetChanged();
        }
    }

    public void addExpenses(List<Expense> moreExpenses) {
        int startPosition = expenses.size();
        expenses.addAll(moreExpenses);
        notifyItemRangeInserted(startPosition, moreExpenses.size());
    }

    public void removeExpense(int position) {
        if (position >= 0 && position < expenses.size()) {
            expenses.remove(position);
            notifyItemRemoved(position);
        }
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private final ItemExpenseBinding binding;

        public ExpenseViewHolder(ItemExpenseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Expense expense) {
            binding.tvAmount.setText(String.valueOf(expense.getAmount()));
            binding.tvCurrency.setText(expense.getCurrency());
            binding.tvRemark.setText(expense.getNote());
            binding.tvCategory.setText(expense.getCategory());
            binding.tvDate.setText(expense.getDate());

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(expense);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Expense expense);
    }
}