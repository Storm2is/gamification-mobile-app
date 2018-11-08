package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storm.stormreview.R;

import java.util.List;

import activities.MenuActivity;
import models.User;
import models.UserBadge;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {


    private Context mContext;
    private List<UserBadge> badgesList;

    public class BadgeViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView badgeImg;
        Button badgeName;



        public BadgeViewHolder(@NonNull View itemView) {
            super(itemView);

            badgeName = (Button) itemView.findViewById(R.id.badgename);
            userName = (TextView) itemView.findViewById(R.id.username);
            badgeImg = (ImageView) itemView.findViewById(R.id.badgeImg);
        }
    }

    public BadgeAdapter(List<UserBadge> badgesList, Context context) {
        this.badgesList = badgesList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.badge_card, viewGroup, false);

        return new BadgeAdapter.BadgeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder badgeViewHolder, int i) {
        UserBadge badge = badgesList.get(i);
        badgeViewHolder.userName.setText(badge.getUser().getUsername());
        badgeViewHolder.badgeName.setText(badge.getBadge().replaceAll("_", " ").toUpperCase());
        int id = mContext.getResources().getIdentifier( badge.getBadge(), "drawable", mContext.getPackageName());
        badgeViewHolder.badgeImg.setBackgroundResource(id);

    }

    @Override
    public int getItemCount() {
        return badgesList.size();
    }

}
