package com.example.blooddonor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.blooddonor.DonationHistoryActivity;
import com.example.blooddonor.DonorCardActivity;
import com.example.blooddonor.HospitalListActivity;
import com.example.blooddonor.LoginActivity;
import com.example.blooddonor.R;
import com.example.blooddonor.RewardsActivity;
import com.example.blooddonor.utils.SessionManager;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);

        SessionManager session = new SessionManager(getActivity());

        TextView tvUserName   = view.findViewById(R.id.tvUserName);
        TextView tvUserEmail  = view.findViewById(R.id.tvUserEmail);
        TextView tvAvatar     = view.findViewById(R.id.tvAvatar);
        TextView tvBloodGroup = view.findViewById(R.id.tvProfileBloodGroup);
        TextView tvUserType   = view.findViewById(R.id.tvUserType);

        String name = session.getUserName();
        tvUserName.setText(name);
        tvUserEmail.setText(session.getUserEmail());
        tvAvatar.setText(name.isEmpty() ? "U" : String.valueOf(name.charAt(0)).toUpperCase());
        tvBloodGroup.setText("🩸 " + session.getBloodGroup());
        tvUserType.setText("  " + session.getUserType());

        view.findViewById(R.id.menuDonationHistory).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), DonationHistoryActivity.class)));

        view.findViewById(R.id.menuDonorCard).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), DonorCardActivity.class)));

        view.findViewById(R.id.menuRewards).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), RewardsActivity.class)));

        view.findViewById(R.id.menuHospitals).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), HospitalListActivity.class)));

        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            session.clearSession();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
