package com.example.blooddonor.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.R;
import com.example.blooddonor.model.User;
import com.example.blooddonor.utils.DistanceCalculator;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder> {

    private static final double USER_LAT = 23.0225;
    private static final double USER_LON = 72.5714;

    private List<User> donorList;

    public DonorAdapter(List<User> donorList) {
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donor_card, parent, false);
        return new DonorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        User donor = donorList.get(position);

        String initial = donor.getFullName().isEmpty()
                ? "?" : String.valueOf(donor.getFullName().charAt(0)).toUpperCase();
        holder.tvAvatar.setText(initial);

        holder.tvName.setText(donor.getFullName());
        holder.tvBloodGroup.setText(donor.getBloodGroup());

        double distance = DistanceCalculator.getDistance(
                USER_LAT, USER_LON, donor.getLatitude(), donor.getLongitude());
        holder.tvDistance.setText("📍 " + DistanceCalculator.formatDistance(distance));

        if (donor.isAvailable()) {
            holder.tvStatus.setText("Available");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(R.color.green_available));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_available);
        } else {
            holder.tvStatus.setText("Not Available");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(R.color.red_primary));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_busy);
        }

        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + donor.getPhone()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public void updateList(List<User> newList) {
        this.donorList = newList;
        notifyDataSetChanged();
    }

    static class DonorViewHolder extends RecyclerView.ViewHolder {
        TextView tvAvatar, tvName, tvBloodGroup, tvDistance, tvStatus;
        Button btnCall;

        DonorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAvatar     = itemView.findViewById(R.id.tvDonorAvatar);
            tvName       = itemView.findViewById(R.id.tvDonorName);
            tvBloodGroup = itemView.findViewById(R.id.tvDonorBloodGroup);
            tvDistance   = itemView.findViewById(R.id.tvDonorDistance);
            tvStatus     = itemView.findViewById(R.id.tvDonorStatus);
            btnCall      = itemView.findViewById(R.id.btnCallDonor);
        }
    }
}
