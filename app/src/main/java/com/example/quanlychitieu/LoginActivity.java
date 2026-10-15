package com.example.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Tài khoản cố định (hard-code)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    private EditText edtUsername, edtPassword;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvError = findViewById(R.id.tvError);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String user = edtUsername.getText().toString().trim();
        String pass = edtPassword.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            showError("Vui lòng nhập tên đăng nhập và mật khẩu");
            return;
        }
        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            tvError.setVisibility(View.GONE);
            startActivity(new Intent(this, MainActivity.class));
            finish(); // không quay lại màn hình đăng nhập bằng nút Back
        } else {
            showError("Sai tên đăng nhập hoặc mật khẩu");
        }
    }

    private void showError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(View.VISIBLE);
    }
}
