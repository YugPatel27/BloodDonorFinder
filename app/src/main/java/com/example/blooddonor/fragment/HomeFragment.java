package com.example.blooddonor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.DonorCardActivity;
import com.example.blooddonor.HospitalListActivity;
import com.example.blooddonor.PostRequestActivity;
import com.example.blooddonor.R;
import com.example.blooddonor.adapter.RequestAdapter;
import com.example.blooddonor.model.BloodRequest;
import com.example.blooddonor.utils.DummyData;
import com.example.blooddonor.utils.SessionManager;

import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionManager session = new SessionManager(getActivity());

        TextView tvWelcome         = view.findViewById(R.id.tvWelcome);
        TextView tvBloodGroupBadge = view.findViewById(R.id.tvBloodGroupBadge);
        TextView tvAvailability    = view.findViewById(R.id.tvAvailabilityStatus);
        TextView tvMyDonations     = view.findViewById(R.id.tvMyDonations);

        tvWelcome.setText("Hello, " + session.getUserName() + " 👋");
        tvBloodGroupBadge.setText("🩸 " + session.getBloodGroup());
        tvAvailability.setText(session.isAvailable() ? "● Available" : "● Not Available");
        tvMyDonations.setText(String.valueOf(session.getTotalDonations()));

        view.findViewById(R.id.actionFindDonor).setOnClickListener(v ->
                getActivity().findViewById(R.id.nav_search).performClick());

        view.findViewById(R.id.actionPostRequest).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), PostRequestActivity.class)));

        view.findViewById(R.id.actionMyCard).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), DonorCardActivity.class)));

        view.findViewById(R.id.actionHospitals).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), HospitalListActivity.class)));

        RecyclerView rvRequests = view.findViewById(R.id.rvNearbyRequests);
        rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<BloodRequest> allRequests = DummyData.getSampleRequests();
        List<BloodRequest> nearbyRequests = allRequests.subList(0, Math.min(3, allRequests.size()));

        rvRequests.setAdapter(new RequestAdapter(nearbyRequests));
    }
}
