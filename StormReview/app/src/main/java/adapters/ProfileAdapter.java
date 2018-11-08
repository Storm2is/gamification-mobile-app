package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storm.stormreview.R;

import java.util.List;

import activities.LoginActivity;
import activities.MenuActivity;
import models.User;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context mContext;
    private List<User> usersList;
    private final OnItemClickListener listener;


    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView profileName;
        ImageView profileImg;



        public ProfileViewHolder(@NonNull View v) {
            super(v);
            profileName = (TextView) v.findViewById(R.id.nameProfile);
            profileImg = (ImageView) v.findViewById(R.id.imgProfile);

        }



    public void bind(final User item, final OnItemClickListener listener) {


        itemView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                listener.onItemClick(item);
            }

        });

    }



}


    public ProfileAdapter(List<User> usersList, Context context, OnItemClickListener listener) {
        this.usersList = usersList;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profile_card, viewGroup, false);

        return new ProfileAdapter.ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, final int i) {
        User user = usersList.get(i);
        profileViewHolder.profileName.setText(user.getUsername().toUpperCase());
        int id = mContext.getResources().getIdentifier("user" + String.valueOf(user.getId()), "drawable", mContext.getPackageName());
        profileViewHolder.profileImg.setBackgroundResource(id);

        profileViewHolder.profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                User user = usersList.get(i);
                intent.putExtra("userId",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }







}
