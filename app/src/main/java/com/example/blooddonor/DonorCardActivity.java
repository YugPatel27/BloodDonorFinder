package com.example.blooddonor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonor.utils.SessionManager;

public class DonorCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_card);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Donor Card");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SessionManager session = new SessionManager(this);

        TextView tvBloodGroup = findViewById(R.id.tvCardBloodGroup);
        TextView tvName       = findViewById(R.id.tvCardName);
        TextView tvAvatar     = findViewById(R.id.tvCardAvatar);
        TextView tvDonorId    = findViewById(R.id.tvCardDonorId);
        TextView tvDonations  = findViewById(R.id.tvCardDonations);
        TextView tvNextDate   = findViewById(R.id.tvCardNextDate);
        Button btnShare       = findViewById(R.id.btnShareCard);

        String name = session.getUserName();
        tvBloodGroup.setText(session.getBloodGroup());
        tvName.setText(name);
        tvAvatar.setText(name.isEmpty() ? "U" : String.valueOf(name.charAt(0)).toUpperCase());
        tvDonorId.setText("ID: BD" + session.getUserId().substring(Math.max(0, session.getUserId().length() - 5)));
        tvDonations.setText(session.getTotalDonations() + " donations");

        String lastDonation = session.getLastDonation();
        if (lastDonation.isEmpty() || lastDonation.equals("Not donated yet")) {
            tvNextDate.setText("Eligible Now ✅");
        } else {
            tvNextDate.setText("90 days after " + lastDonation);
        }

        btnShare.setOnClickListener(v -> {
            String shareText = "🩸 I'm a blood donor!\n\n"
                    + "Name: " + name + "\n"
                    + "Blood Group: " + session.getBloodGroup() + "\n"
                    + "Total Donations: " + session.getTotalDonations() + "\n\n"
                    + "Download Blood Donor App to find donors near you!";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share Donor Card via"));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
