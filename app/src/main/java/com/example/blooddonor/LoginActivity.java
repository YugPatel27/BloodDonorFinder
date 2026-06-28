package com.example.blooddonor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonor.database.UserDao;
import com.example.blooddonor.model.User;
import com.example.blooddonor.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private SessionManager sessionManager;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        userDao        = new UserDao(this);

        if (sessionManager.isLoggedIn()) {
            goToDashboard();
            return;
        }

        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Please enter your email");
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Please enter your password");
                return;
            }

            User user = userDao.getUserByEmail(email);

            if (user == null) {
                Toast.makeText(this, "❌ No account found with this email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!user.getPassword().equals(password)) {
                Toast.makeText(this, "❌ Incorrect password", Toast.LENGTH_SHORT).show();
                return;
            }

            sessionManager.saveSession(
                    user.getUserId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getBloodGroup(),
                    user.getUserType(),
                    user.isAvailable(),
                    user.getLastDonationDate(),
                    user.getTotalDonations(),
                    user.getPoints()
            );

            Toast.makeText(this, "✅ Welcome back, " + user.getFullName() + "!", Toast.LENGTH_SHORT).show();
            goToDashboard();
        });

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void goToDashboard() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
