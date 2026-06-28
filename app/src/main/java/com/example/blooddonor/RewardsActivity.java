package com.example.blooddonor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.adapter.BadgeAdapter;
import com.example.blooddonor.utils.Constants;
import com.example.blooddonor.utils.DummyData;
import com.example.blooddonor.utils.SessionManager;

public class RewardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rewards & Badges");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SessionManager session = new SessionManager(this);
        int totalDonations = session.getTotalDonations();
        int points = totalDonations * Constants.POINTS_PER_DONATION;

        TextView tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("⭐ Your Points: " + points);

        RecyclerView rvBadges = findViewById(R.id.rvBadges);
        rvBadges.setLayoutManager(new LinearLayoutManager(this));
        rvBadges.setAdapter(new BadgeAdapter(DummyData.getAllBadges(totalDonations)));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
