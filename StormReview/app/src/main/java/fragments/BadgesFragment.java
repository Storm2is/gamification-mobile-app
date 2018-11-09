package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storm.stormreview.R;

import java.util.ArrayList;
import java.util.List;

import adapters.BadgeAdapter;
import adapters.ProfileAdapter;
import adapters.UserAdapter;
import models.User;
import models.UserBadge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.ApiClient;
import services.UserClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BadgesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BadgesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class BadgesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private List<UserBadge> badgesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BadgeAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BadgesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BadgesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BadgesFragment newInstance(String param1, String param2) {
        BadgesFragment fragment = new BadgesFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_badges, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_badges);

        layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        UserClient service = ApiClient.getClient().create(UserClient.class);
        Call<List<UserBadge>> call = service.getBadges();
        call.enqueue(new Callback<List<UserBadge>>() {
            @Override
            public void onResponse(Call<List<UserBadge>> call, Response<List<UserBadge>> response) {
                badgesList = response.body();
                Log.e("success", "Number of  badges received: " + badgesList.size());
                Log.e("badges list", badgesList.get(1).getBadge());
                mAdapter = new BadgeAdapter(badgesList, getActivity());
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UserBadge>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("failure", t.getMessage());

            }
        });


        // Inflate the layout for this fragment
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
