package com.example.blooddonor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.adapter.HistoryAdapter;
import com.example.blooddonor.database.DonationHistoryDao;
import com.example.blooddonor.model.DonationHistory;
import com.example.blooddonor.utils.DummyData;
import com.example.blooddonor.utils.SessionManager;

import java.util.List;

public class DonationHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Donation History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SessionManager session = new SessionManager(this);
        DonationHistoryDao historyDao = new DonationHistoryDao(this);

        TextView tvNextEligible = findViewById(R.id.tvNextEligible);
        String lastDonation = session.getLastDonation();
        if (lastDonation.isEmpty() || lastDonation.equals("Not donated yet")) {
            tvNextEligible.setText("Next eligible: Available Now ✅");
        } else {
            tvNextEligible.setText("Next eligible: 90 days after " + lastDonation);
        }

        String userId = session.getUserId();
        List<DonationHistory> historyList = historyDao.getHistoryByUser(userId);

        if (historyList == null || historyList.isEmpty()) {
            historyList = DummyData.getSampleHistory();
        }

        RecyclerView rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(new HistoryAdapter(historyList));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
