package activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storm.stormreview.R;

import java.util.ArrayList;
import java.util.List;

import adapters.OnItemClickListener;
import adapters.ProfileAdapter;
import adapters.UserAdapter;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.ApiClient;
import services.UserClient;

public class LoginActivity extends AppCompatActivity {


    private List<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProfileAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Identify Yourself !");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_profiles);

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<User>> call = service.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                usersList = response.body();
                Log.e("success", "Number of users received: " + usersList.size());
                Log.e("users list", usersList.toString());
                mAdapter = new ProfileAdapter(usersList, LoginActivity.this, new OnItemClickListener() {
                    @Override
                    public void onItemClick(User item) {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("userId",item.getId());

                        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("userId",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("userId",item.getId());
                        editor.apply();
                        LoginActivity.this.startActivity(intent);


                    }
                });
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("failure",t.getMessage());

            }
        });



    }
}
