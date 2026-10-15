package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        Expense e = (Expense) getIntent().getSerializableExtra(MainActivity.EXTRA_EXPENSE);
        int position = getIntent().getIntExtra(MainActivity.EXTRA_POSITION, -1);
        if (e == null) {
            finish();
            return;
        }

        ((TextView) findViewById(R.id.tvName)).setText(e.getName());
        ((TextView) findViewById(R.id.tvAmount)).setText(ExpenseAdapter.formatMoney(e.getAmount()));
        ((TextView) findViewById(R.id.tvCategory)).setText("Danh mục: " + e.getCategory());
        ((TextView) findViewById(R.id.tvDate)).setText("Ngày chi tiêu: " + e.getDate());
        String note = e.getNote().isEmpty() ? "(Không có)" : e.getNote();
        ((TextView) findViewById(R.id.tvNote)).setText("Ghi chú: " + note);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra(MainActivity.EXTRA_DELETE, true);
            result.putExtra(MainActivity.EXTRA_POSITION, position);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}
