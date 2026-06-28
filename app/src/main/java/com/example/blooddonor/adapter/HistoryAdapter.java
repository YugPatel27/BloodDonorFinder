package com.example.blooddonor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.R;
import com.example.blooddonor.model.DonationHistory;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<DonationHistory> historyList;

    public HistoryAdapter(List<DonationHistory> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_card, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        DonationHistory h = historyList.get(position);
        holder.tvHospital.setText(h.getHospitalName());
        holder.tvDate.setText(h.getDate());
        holder.tvUnits.setText(h.getUnitsGiven());
        holder.tvBloodGroup.setText(h.getBloodGroup());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospital, tvDate, tvUnits, tvBloodGroup;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospital   = itemView.findViewById(R.id.tvHistoryHospital);
            tvDate       = itemView.findViewById(R.id.tvHistoryDate);
            tvUnits      = itemView.findViewById(R.id.tvHistoryUnits);
            tvBloodGroup = itemView.findViewById(R.id.tvHistoryBloodGroup);
        }
    }
}
