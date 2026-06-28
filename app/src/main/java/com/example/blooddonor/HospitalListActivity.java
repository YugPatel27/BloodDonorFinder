package com.example.blooddonor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.adapter.HospitalAdapter;
import com.example.blooddonor.utils.DummyData;

public class HospitalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hospitals & Blood Banks");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rvHospitals = findViewById(R.id.rvHospitals);
        rvHospitals.setLayoutManager(new LinearLayoutManager(this));
        rvHospitals.setAdapter(new HospitalAdapter(DummyData.getSampleHospitals()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
