package fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storm.stormreview.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activities.LoginActivity;
import activities.MenuActivity;
import adapters.OnItemClickListener;
import adapters.ProfileAdapter;
import models.User;
import models.WelcomeStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.ApiClient;
import services.UserClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //private List<User> usersList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       /* UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<User>> call = service.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                usersList = response.body();
                Log.e("success", "Number of users received: " + usersList.size());
                Log.e("users list home page", usersList.toString());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("failure homePage",t.getMessage());

            }
        });
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button pointsBtn = view.findViewById(R.id.buttonP);
        Button messageBtn = view.findViewById(R.id.buttonM);

        //get user id
        SharedPreferences sharedPref = getActivity().getSharedPreferences("userId",Context.MODE_PRIVATE);
        int userId = sharedPref.getInt("userId", 20);
        Log.i("UserIdShared ",String.valueOf(userId));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //usersList = getUsers();

        // Get list of users and points from REST
        UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<User>> call = service.getUsers();
        List<User> usersList = new ArrayList<>();
        try {
            usersList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get the logged in user
        User user = usersList.get(userId - 1);
        Log.i("user points",String.valueOf(user.getPoint().getValue()));
        // get current userPoints ==> 120
        setAvatar(view, user.getPoint().getValue());
        pointsBtn.setText("Your Points: " + String.valueOf(user.getPoint().getValue()) + " Pts");

        int totalPoints = 0;
        for (models.User u : usersList) {
            totalPoints += u.getPoint().getValue();
        }
        float avgPoints = totalPoints / usersList.size();
        if (user.getPoint().getValue() < avgPoints) {
            setWelcomeMessage(view, WelcomeStatus.Sad);
        } else if (user.getPoint().getValue() > avgPoints && user.getPoint().getValue() < (avgPoints * 1.5)) {
            setWelcomeMessage(view, WelcomeStatus.Happy);
        } else {
            setWelcomeMessage(view, WelcomeStatus.Super);
        }

        int poitnsToNextLevel = getPointsToTheNextLevel(user.getPoint().getValue());
        messageBtn.setText("You need " + String.valueOf(poitnsToNextLevel) + " Pts to reach next Level !");

        return view;
    }

    private List<User> getUsers() {
        models.Point point1 = new models.Point(1, 10);
        models.Point point2 = new models.Point(1, 75);
        models.Point point3 = new models.Point(1, 200);
        models.Point point4 = new models.Point(1, 125);
        models.Point point5 = new models.Point(1, 111);

        List<models.User> usersList = new ArrayList<>();
        models.User user = new models.User("Tamer", 1, "email", point1);
        usersList.add(user);

        models.User user1 = new models.User("Rayhane", 2, "email", point2);
        usersList.add(user1);

        models.User user2 = new models.User("Oluchi", 3, "email", point3);
        usersList.add(user2);

        models.User user3 = new models.User("Phuong", 4, "email", point4);
        usersList.add(user3);

        models.User user4 = new models.User("Mikhail", 5, "email", point5);
        usersList.add(user4);

        models.User user5 = new models.User("Shujun", 6, "email", point5);
        usersList.add(user5);

        models.User user6 = new models.User("Collective", 7, "email", point4);
        usersList.add(user6);

        return usersList;
    }

    private void setWelcomeMessage(View view, WelcomeStatus status) {
        Button btn = view.findViewById(R.id.button);

        Drawable img = null;
        String message = "Welcome";
        switch (status) {
            case Sad: {
                img = getContext().getResources().getDrawable(R.drawable.sad);
                message = "Your are below team average! ";
                break;
            }
            case Happy: {
                img = getContext().getResources().getDrawable(R.drawable.happy);
                message = " you are above team average, Keep it up! ";
                break;
            }
            case Super: {
                img = getContext().getResources().getDrawable(R.drawable.in_love);
                message = "Bravo! you are way above team average ";
                break;
            }
        }
        img.setBounds(0, 0, 80, 80);
        btn.setCompoundDrawables(null, null, img, null);
        btn.setText(message);


    }

    private int getPointsToTheNextLevel(int point) {
        if (point == 0 || (point > 0 && point < 25)) {
            return 25 - point;
        } else if (point >= 25 && point < 100) {
            return 100 - point;
        } else {
            return 400 - point;
        }
    }

    private void setAvatar(View view, int point) {
        ImageView avatar = (ImageView) view.findViewById(R.id.penguin);
        if (point == 0) {
            avatar.setBackgroundResource(R.drawable.penguin_0);
        } else if (point > 0 && point < 25) {
            avatar.setBackgroundResource(R.drawable.penguin_0_0);
        } else if (point >= 25 && point < 50) {
            avatar.setBackgroundResource(R.drawable.penguin_1);
        } else if (point >= 50 && point < 100) {
            avatar.setBackgroundResource(R.drawable.penguin_1_1);
        } else if (point >= 100 && point < 125) {
            avatar.setBackgroundResource(R.drawable.penguin_2);
        } else if (point >= 125 && point < 175) {
            avatar.setBackgroundResource(R.drawable.penguin_2_2);
        } else if (point >= 175 && point < 250) {
            avatar.setBackgroundResource(R.drawable.penguin_3);
        } else if (point >= 250 && point < 400) {
            avatar.setBackgroundResource(R.drawable.penguin_3_3);
        } else {
            avatar.setBackgroundResource(R.drawable.penguin_5);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
