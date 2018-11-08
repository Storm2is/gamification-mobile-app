package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storm.stormreview.R;

import java.util.List;

import models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private List<User> usersList;


    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView points;
        ImageView avatar;


        public UserViewHolder(@NonNull View v) {
            super(v);
            username = (TextView) v.findViewById(R.id.username);
            points = (TextView) v.findViewById(R.id.points);
            avatar = (ImageView) v.findViewById(R.id.avatar);

        }
    }

    public UserAdapter(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_card, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder userViewHolder, int i) {
        User user = usersList.get(i);
        userViewHolder.username.setText(user.getUsername());
        userViewHolder.points.setText(String.valueOf(user.getPoint().getValue()) + " Pts");
        int id = mContext.getResources().getIdentifier("user" + String.valueOf(user.getId()), "drawable", mContext.getPackageName());
        userViewHolder.avatar.setBackgroundResource(id);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

}
