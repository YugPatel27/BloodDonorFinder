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
import com.example.blooddonor.model.BloodRequest;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<BloodRequest> requestList;

    public RequestAdapter(List<BloodRequest> requestList) {
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_card, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        BloodRequest req = requestList.get(position);

        holder.tvBloodGroup.setText(req.getBloodGroup());
        holder.tvHospital.setText("🏥 " + req.getHospitalName());
        holder.tvDateTime.setText("🕐 " + req.getRequiredDateTime());
        holder.tvRequester.setText("👤 Posted by: " + req.getRequesterName());

        String priority = req.getPriority();
        holder.tvPriority.setText(priority);

        switch (priority) {
            case "Critical":
                holder.tvPriority.setText("🚨 Critical");
                holder.tvPriority.setTextColor(holder.itemView.getContext().getColor(R.color.red_dark));
                holder.tvPriority.setBackgroundResource(R.drawable.bg_priority_critical);
                break;
            case "Urgent":
                holder.tvPriority.setText("⚠ Urgent");
                holder.tvPriority.setTextColor(holder.itemView.getContext().getColor(R.color.orange_urgent));
                holder.tvPriority.setBackgroundResource(R.drawable.bg_priority_urgent);
                break;
            default:
                holder.tvPriority.setText("✅ Normal");
                holder.tvPriority.setTextColor(holder.itemView.getContext().getColor(R.color.green_available));
                holder.tvPriority.setBackgroundResource(R.drawable.bg_priority_normal);
                break;
        }

        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + req.getRequesterPhone()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public void updateList(List<BloodRequest> newList) {
        this.requestList = newList;
        notifyDataSetChanged();
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvBloodGroup, tvPriority, tvHospital, tvDateTime, tvRequester;
        Button btnCall;

        RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBloodGroup = itemView.findViewById(R.id.tvRequestBloodGroup);
            tvPriority   = itemView.findViewById(R.id.tvPriority);
            tvHospital   = itemView.findViewById(R.id.tvHospitalName);
            tvDateTime   = itemView.findViewById(R.id.tvRequiredDateTime);
            tvRequester  = itemView.findViewById(R.id.tvRequesterName);
            btnCall      = itemView.findViewById(R.id.btnCallRequester);
        }
    }
}
