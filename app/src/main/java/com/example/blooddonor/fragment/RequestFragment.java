package com.example.blooddonor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.PostRequestActivity;
import com.example.blooddonor.R;
import com.example.blooddonor.adapter.RequestAdapter;
import com.example.blooddonor.database.BloodRequestDao;
import com.example.blooddonor.model.BloodRequest;
import com.example.blooddonor.utils.DummyData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RequestFragment extends Fragment {

    private RecyclerView rvRequests;
    private RequestAdapter adapter;
    private BloodRequestDao bloodRequestDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);

        bloodRequestDao = new BloodRequestDao(getActivity());

        rvRequests = view.findViewById(R.id.rvRequests);
        rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadRequests();

        FloatingActionButton fab = view.findViewById(R.id.fabAddRequest);
        fab.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), PostRequestActivity.class)));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rvRequests != null) {
            loadRequests();
        }
    }

    private void loadRequests() {
        List<BloodRequest> requests = bloodRequestDao.getAllRequests();

        if (requests == null || requests.isEmpty()) {
            requests = DummyData.getSampleRequests();
        }

        if (adapter == null) {
            adapter = new RequestAdapter(requests);
            rvRequests.setAdapter(adapter);
        } else {
            adapter.updateList(requests);
        }
    }
}
