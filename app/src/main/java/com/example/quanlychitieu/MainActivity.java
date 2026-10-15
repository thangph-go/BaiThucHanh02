package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_EXPENSE = "expense";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DELETE = "delete";

    private final ArrayList<Expense> expenses = new ArrayList<>();
    private ExpenseAdapter adapter;
    private TextView tvTotal;

    // Nhận kết quả từ màn hình Thêm
    private final ActivityResultLauncher<Intent> addLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Expense e = (Expense) result.getData().getSerializableExtra(EXTRA_EXPENSE);
                    if (e != null) {
                        expenses.add(e);
                        refresh();
                    }
                }
            });

    // Nhận kết quả từ màn hình Chi tiết (xóa)
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null
                        && result.getData().getBooleanExtra(EXTRA_DELETE, false)) {
                    int pos = result.getData().getIntExtra(EXTRA_POSITION, -1);
                    if (pos >= 0 && pos < expenses.size()) {
                        expenses.remove(pos);
                        refresh();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotal = findViewById(R.id.tvTotal);
        ListView lvExpenses = findViewById(R.id.lvExpenses);
        Button btnAdd = findViewById(R.id.btnAdd);

        // Dữ liệu mẫu
        String today = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        expenses.add(new Expense("Ăn sáng", 30000, "Ăn uống", today, ""));
        expenses.add(new Expense("Đổ xăng", 50000, "Đi lại", today, ""));
        expenses.add(new Expense("Mua sách", 120000, "Học tập", today, ""));

        adapter = new ExpenseAdapter(this, expenses);
        lvExpenses.setAdapter(adapter);
        updateTotal();

        btnAdd.setOnClickListener(v ->
                addLauncher.launch(new Intent(this, AddExpenseActivity.class)));

        lvExpenses.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, ExpenseDetailActivity.class);
            i.putExtra(EXTRA_EXPENSE, expenses.get(position));
            i.putExtra(EXTRA_POSITION, position);
            detailLauncher.launch(i);
        });
    }

    private void refresh() {
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    private void updateTotal() {
        long total = 0;
        for (Expense e : expenses) total += e.getAmount();
        tvTotal.setText("Tổng đã chi: " + ExpenseAdapter.formatMoney(total));
    }
}
