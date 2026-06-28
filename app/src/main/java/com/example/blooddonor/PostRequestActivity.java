package com.example.blooddonor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonor.database.BloodRequestDao;
import com.example.blooddonor.model.BloodRequest;
import com.example.blooddonor.utils.Constants;
import com.example.blooddonor.utils.SessionManager;

import java.util.Calendar;

public class PostRequestActivity extends AppCompatActivity {

    private Spinner spinnerBloodGroup;
    private EditText etHospital, etContact;
    private TextView tvRequiredDate;
    private RadioGroup rgPriority;
    private Button btnSubmit;
    private String selectedDateTime = "";

    private BloodRequestDao bloodRequestDao;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Post Blood Request");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        bloodRequestDao = new BloodRequestDao(this);
        sessionManager  = new SessionManager(this);

        spinnerBloodGroup = findViewById(R.id.spinnerBloodGroup);
        etHospital        = findViewById(R.id.etHospitalName);
        etContact         = findViewById(R.id.etContact);
        tvRequiredDate    = findViewById(R.id.tvRequiredDate);
        rgPriority        = findViewById(R.id.rgPriority);
        btnSubmit         = findViewById(R.id.btnSubmitRequest);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.BLOOD_GROUPS_ONLY);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapter);

        etContact.setText(sessionManager.getUserPhone());

        tvRequiredDate.setOnClickListener(v -> pickDateTime());
        btnSubmit.setOnClickListener(v -> submitRequest());
    }

    private void pickDateTime() {
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, (view, y, m, d) -> {
            new TimePickerDialog(this, (tv, hour, minute) -> {
                selectedDateTime = d + "/" + (m + 1) + "/" + y
                        + ", " + String.format("%02d:%02d", hour, minute);
                tvRequiredDate.setText(selectedDateTime);
                tvRequiredDate.setTextColor(getColor(R.color.text_primary));
            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void submitRequest() {
        String bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
        String hospital   = etHospital.getText().toString().trim();
        String contact    = etContact.getText().toString().trim();

        if (hospital.isEmpty()) { etHospital.setError("Hospital name required"); return; }
        if (selectedDateTime.isEmpty()) {
            Toast.makeText(this, "Please select required date and time", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contact.isEmpty()) { etContact.setError("Contact number required"); return; }

        int selectedPriorityId = rgPriority.getCheckedRadioButtonId();
        String priority = "Normal";
        if (selectedPriorityId == R.id.rbUrgent)        priority = "Urgent";
        else if (selectedPriorityId == R.id.rbCritical) priority = "Critical";

        Calendar today = Calendar.getInstance();
        String postedDate = today.get(Calendar.DAY_OF_MONTH) + " "
                + today.getDisplayName(Calendar.MONTH, Calendar.SHORT, getResources().getConfiguration().locale)
                + " " + today.get(Calendar.YEAR);

        BloodRequest request = new BloodRequest();
        request.setRequesterName(sessionManager.getUserName());
        request.setRequesterPhone(contact);
        request.setBloodGroup(bloodGroup);
        request.setHospitalName(hospital);
        request.setRequiredDateTime(selectedDateTime);
        request.setPriority(priority);
        request.setLatitude(0.0);
        request.setLongitude(0.0);
        request.setPostedDate(postedDate);

        long rowId = bloodRequestDao.insertRequest(request, sessionManager.getUserId());

        if (rowId == -1) {
            Toast.makeText(this, "❌ Failed to post request. Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this,
                "✅ Request posted for " + bloodGroup + " blood at " + hospital
                        + "!\nNearby donors have been notified.",
                Toast.LENGTH_LONG).show();

        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
