package com.example.blooddonor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blooddonor.R;
import com.example.blooddonor.model.Badge;

import java.util.List;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {

    private List<Badge> badgeList;

    public BadgeAdapter(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_badge_card, parent, false);
        return new BadgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Badge badge = badgeList.get(position);
        holder.tvEmoji.setText(badge.getEmoji());
        holder.tvName.setText(badge.getName());
        holder.tvDesc.setText(badge.getDescription());

        if (badge.isEarned()) {
            holder.tvStatus.setText("✅");
            holder.tvEmoji.setAlpha(1.0f);
            holder.tvName.setAlpha(1.0f);
        } else {
            holder.tvStatus.setText("🔒");
            holder.tvEmoji.setAlpha(0.4f);
            holder.tvName.setAlpha(0.5f);
        }
    }

    @Override
    public int getItemCount() {
        return badgeList.size();
    }

    static class BadgeViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmoji, tvName, tvDesc, tvStatus;

        BadgeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmoji  = itemView.findViewById(R.id.tvBadgeEmoji);
            tvName   = itemView.findViewById(R.id.tvBadgeName);
            tvDesc   = itemView.findViewById(R.id.tvBadgeDescription);
            tvStatus = itemView.findViewById(R.id.tvBadgeStatus);
        }
    }
}
