package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.stormreview.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.UserAdapter;
import Models.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeaderBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeaderBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDoalog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderBoardFragment newInstance(String param1, String param2) {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
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


    }

    private void prepareUsersData() {

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

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leader_board, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

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
        mAdapter = new UserAdapter(usersList, getActivity());

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        prepareUsersData();
        return view;
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
