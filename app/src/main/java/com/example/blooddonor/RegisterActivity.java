package com.example.blooddonor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonor.database.UserDao;
import com.example.blooddonor.model.User;
import com.example.blooddonor.utils.Constants;
import com.example.blooddonor.utils.SessionManager;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etPhone, etEmail, etPassword;
    private Spinner spinnerBloodGroup;
    private RadioGroup rgUserType;
    private RadioButton rbDonor, rbRequester;
    private TextView tvDonationDate;
    private Switch switchAvailability;
    private Button btnRegister;
    private TextView tvLogin;

    private String selectedDate = "";
    private SessionManager sessionManager;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sessionManager = new SessionManager(this);
        userDao        = new UserDao(this);

        etName             = findViewById(R.id.etName);
        etPhone            = findViewById(R.id.etPhone);
        etEmail            = findViewById(R.id.etEmail);
        etPassword         = findViewById(R.id.etPassword);
        spinnerBloodGroup  = findViewById(R.id.spinnerBloodGroup);
        rgUserType         = findViewById(R.id.rgUserType);
        rbDonor            = findViewById(R.id.rbDonor);
        rbRequester        = findViewById(R.id.rbRequester);
        tvDonationDate     = findViewById(R.id.tvDonationDate);
        switchAvailability = findViewById(R.id.switchAvailability);
        btnRegister        = findViewById(R.id.btnRegister);
        tvLogin            = findViewById(R.id.tvLogin);

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Constants.BLOOD_GROUPS_ONLY
        );
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(bloodAdapter);

        tvDonationDate.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            int year  = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day   = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this,
                    (view, y, m, d) -> {
                        selectedDate = d + "/" + (m + 1) + "/" + y;
                        tvDonationDate.setText(selectedDate);
                        tvDonationDate.setTextColor(getColor(R.color.text_primary));
                    }, year, month, day);
            dialog.show();
        });

        btnRegister.setOnClickListener(v -> attemptRegistration());
        tvLogin.setOnClickListener(v -> finish());
    }

    private void attemptRegistration() {
        String name       = etName.getText().toString().trim();
        String phone      = etPhone.getText().toString().trim();
        String email      = etEmail.getText().toString().trim();
        String password   = etPassword.getText().toString().trim();
        String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
        boolean isAvailable = switchAvailability.isChecked();

        int selectedId = rgUserType.getCheckedRadioButtonId();
        String userType = (selectedId == R.id.rbDonor) ? "Donor" : "Requester";

        if (name.isEmpty()) {
            etName.setError("Name is required"); return;
        }
        if (phone.isEmpty() || phone.length() != 10) {
            etPhone.setError("Enter valid 10-digit phone number"); return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            etEmail.setError("Enter a valid email address"); return;
        }
        if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters"); return;
        }

        User existing = userDao.getUserByEmail(email);
        if (existing != null) {
            etEmail.setError("This email is already registered");
            Toast.makeText(this, "❌ An account with this email already exists.", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = "USR" + System.currentTimeMillis();
        String lastDonation = selectedDate.isEmpty() ? "Not donated yet" : selectedDate;

        User newUser = new User(
                uid, name, email, phone, password,
                bloodGroup, userType,
                0.0, 0.0,
                lastDonation,
                isAvailable,
                0,
                0
        );

        long rowId = userDao.insertUser(newUser);

        if (rowId == -1) {
            Toast.makeText(this, "❌ Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        sessionManager.saveSession(
                uid, name, email, phone, bloodGroup,
                userType, isAvailable, lastDonation, 0, 0
        );

        Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }
}
