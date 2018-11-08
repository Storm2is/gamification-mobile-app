package activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.storm.stormreview.R;

import java.util.ArrayList;
import java.util.List;

import adapters.UserAdapter;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.ApiClient;
import services.UserClient;

public class TeamProgressActivity extends AppCompatActivity {
    private List<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_progress);
        setTitle("LeaderBoard");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

       /* progressDoalog = new ProgressDialog(TeamProgressActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();*/

        /*Create handle for the RetrofitInstance interface*/
       /* UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<User>> call = service.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressDoalog.dismiss();
                mAdapter = new UserAdapter(response.body(), TeamProgressActivity.this);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(TeamProgressActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }


        });
*/

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true);
        mAdapter = new UserAdapter(usersList, TeamProgressActivity.this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        prepareUsersData();

    }


    private void prepareUsersData() {
        UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<User>> call = service.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mAdapter = new UserAdapter(response.body(), TeamProgressActivity.this);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(TeamProgressActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        /*
        User user = new User("Tamer", 1, "email", 110);
        usersList.add(user);

        User user1 = new User("Rayhane", 2, "email", 150);
        usersList.add(user1);

        User user2 = new User("Oluchi", 3, "email", 233);
        usersList.add(user2);

        User user3 = new User("Phuong", 4, "email", 245);
        usersList.add(user3);

        User user4 = new User("Mikhail", 5, "email", 270);
        usersList.add(user4);

        User user5 = new User("Shujun", 6, "email", 360);
        usersList.add(user5);

        User user6 = new User("Collective", 7, "email", 0);
        usersList.add(user6);
        */
        mAdapter.notifyDataSetChanged();
    }
}
