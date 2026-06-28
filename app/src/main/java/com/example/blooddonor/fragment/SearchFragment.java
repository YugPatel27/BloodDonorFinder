package com.example.blooddonor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.R;
import com.example.blooddonor.adapter.DonorAdapter;
import com.example.blooddonor.database.UserDao;
import com.example.blooddonor.model.User;
import com.example.blooddonor.utils.Constants;
import com.example.blooddonor.utils.DistanceCalculator;
import com.example.blooddonor.utils.DummyData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFragment extends Fragment {

    private static final double USER_LAT = 23.0225;
    private static final double USER_LON = 72.5714;

    private Spinner spinnerBlood, spinnerDistance;
    private CheckBox cbAvailableOnly;
    private Button btnSearch;
    private TextView tvResultCount;
    private RecyclerView rvDonors;
    private DonorAdapter donorAdapter;
    private List<User> allDonors;

    private UserDao userDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);

        userDao = new UserDao(getActivity());

        spinnerBlood    = view.findViewById(R.id.spinnerBloodFilter);
        spinnerDistance = view.findViewById(R.id.spinnerDistance);
        cbAvailableOnly = view.findViewById(R.id.cbAvailableOnly);
        btnSearch       = view.findViewById(R.id.btnSearch);
        tvResultCount   = view.findViewById(R.id.tvResultCount);
        rvDonors        = view.findViewById(R.id.rvDonors);

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, Constants.BLOOD_GROUPS);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlood.setAdapter(bloodAdapter);

        String[] distanceLabels = {"5 km", "10 km", "20 km", "50 km", "100 km"};
        ArrayAdapter<String> distAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, distanceLabels);
        distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistance.setAdapter(distAdapter);

        loadAllDonors();

        rvDonors.setLayoutManager(new LinearLayoutManager(getActivity()));
        donorAdapter = new DonorAdapter(allDonors);
        rvDonors.setAdapter(donorAdapter);
        updateResultCount(allDonors.size());

        btnSearch.setOnClickListener(v -> searchDonors());
        searchDonors();
    }

    private void loadAllDonors() {
        List<User> dbDonors = userDao.getAllDonors();
        allDonors = new ArrayList<>(dbDonors);

        Set<String> existingEmails = new HashSet<>();
        for (User u : dbDonors) {
            existingEmails.add(u.getEmail());
        }

        for (User dummy : DummyData.getSampleDonors()) {
            if (!existingEmails.contains(dummy.getEmail())) {
                allDonors.add(dummy);
            }
        }
    }

    private void searchDonors() {
        String selectedBloodGroup = spinnerBlood.getSelectedItem().toString();
        int distanceIndex  = spinnerDistance.getSelectedItemPosition();
        int maxDistance    = Constants.DISTANCE_OPTIONS[distanceIndex];
        boolean availableOnly = cbAvailableOnly.isChecked();

        List<User> filtered = new ArrayList<>();

        for (User donor : allDonors) {
            boolean bloodMatch = selectedBloodGroup.equals("All")
                    || donor.getBloodGroup().equals(selectedBloodGroup);

            double distance = DistanceCalculator.getDistance(
                    USER_LAT, USER_LON, donor.getLatitude(), donor.getLongitude());
            boolean distanceMatch = distance <= maxDistance;

            boolean availMatch = !availableOnly || donor.isAvailable();

            if (bloodMatch && distanceMatch && availMatch) {
                filtered.add(donor);
            }
        }

        donorAdapter.updateList(filtered);
        updateResultCount(filtered.size());
    }

    private void updateResultCount(int count) {
        tvResultCount.setText(count + " donor" + (count == 1 ? "" : "s") + " found");
    }
}
