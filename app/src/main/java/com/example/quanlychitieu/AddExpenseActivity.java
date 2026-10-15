package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText edtName, edtAmount, edtNote;
    private Spinner spCategory;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        edtName = findViewById(R.id.edtName);
        edtAmount = findViewById(R.id.edtAmount);
        edtNote = findViewById(R.id.edtNote);
        spCategory = findViewById(R.id.spCategory);
        datePicker = findViewById(R.id.datePicker);
        Button btnSave = findViewById(R.id.btnSave);

        String[] categories = {"Ăn uống", "Đi lại", "Học tập", "Giải trí", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        String name = edtName.getText().toString().trim();
        String amountStr = edtAmount.getText().toString().trim();

        if (name.isEmpty()) {
            edtName.setError("Tên khoản chi không được để trống");
            edtName.requestFocus();
            return;
        }
        long amount;
        try {
            amount = Long.parseLong(amountStr);
        } catch (NumberFormatException ex) {
            edtAmount.setError("Số tiền không hợp lệ");
            edtAmount.requestFocus();
            return;
        }
        if (amount <= 0) {
            edtAmount.setError("Số tiền phải lớn hơn 0");
            edtAmount.requestFocus();
            return;
        }

        String date = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());
        String category = spCategory.getSelectedItem().toString();
        String note = edtNote.getText().toString().trim();

        Expense expense = new Expense(name, amount, category, date, note);
        Intent result = new Intent();
        result.putExtra(MainActivity.EXTRA_EXPENSE, expense);
        setResult(RESULT_OK, result);
        Toast.makeText(this, "Đã lưu khoản chi", Toast.LENGTH_SHORT).show();
        finish();
    }
}
