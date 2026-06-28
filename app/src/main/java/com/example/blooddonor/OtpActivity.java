package com.example.blooddonor;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonor.utils.Constants;

public class OtpActivity extends AppCompatActivity {

    private EditText etOtp;
    private Button btnVerify;
    private TextView tvResend;
    private CountDownTimer countDownTimer;
    private boolean canResend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        etOtp     = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);
        tvResend  = findViewById(R.id.tvResend);

        startCountDown();

        btnVerify.setOnClickListener(v -> {
            String enteredOtp = etOtp.getText().toString().trim();

            if (enteredOtp.isEmpty() || enteredOtp.length() != 6) {
                etOtp.setError("Please enter the 6-digit OTP");
                return;
            }

            if (enteredOtp.equals(Constants.DEMO_OTP)) {
                Toast.makeText(this, "✅ Phone verified! Welcome!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OtpActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "❌ Wrong OTP. Try 123456 for demo", Toast.LENGTH_SHORT).show();
            }
        });

        tvResend.setOnClickListener(v -> {
            if (canResend) {
                canResend = false;
                startCountDown();
                Toast.makeText(this, "OTP resent! (Demo: 123456)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCountDown() {
        if (countDownTimer != null) countDownTimer.cancel();

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                tvResend.setText("Resend OTP in 0:" + (seconds < 10 ? "0" + seconds : seconds));
                tvResend.setTextColor(getColor(R.color.text_secondary));
            }

            @Override
            public void onFinish() {
                canResend = true;
                tvResend.setText("Resend OTP");
                tvResend.setTextColor(getColor(R.color.red_primary));
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) countDownTimer.cancel();
    }
}
