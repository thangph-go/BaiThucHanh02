package com.example.quanlychitieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ExpenseAdapter extends ArrayAdapter<Expense> {

    public ExpenseAdapter(@NonNull Context context, @NonNull ArrayList<Expense> list) {
        super(context, 0, list);
    }

    public static String formatMoney(long amount) {
        return NumberFormat.getInstance(new Locale("vi", "VN")).format(amount) + " đ";
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_expense, parent, false);
        }
        Expense e = getItem(position);
        ((TextView) convertView.findViewById(R.id.tvItemName)).setText(e.getName());
        ((TextView) convertView.findViewById(R.id.tvItemAmount)).setText(formatMoney(e.getAmount()));
        ((TextView) convertView.findViewById(R.id.tvItemCategory)).setText(e.getCategory());
        ((TextView) convertView.findViewById(R.id.tvItemDate)).setText(e.getDate());
        return convertView;
    }
}
