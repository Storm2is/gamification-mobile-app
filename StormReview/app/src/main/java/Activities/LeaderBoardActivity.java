package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.storm.stormreview.R;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        setTitle("Best Members");

    }
}
