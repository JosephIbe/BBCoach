package sportsapp.sashi.in.sportsapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sportsapp.sashi.in.sportsapp.R;

public class TodayProgramsFragment extends Fragment {

    private static final String TAG = TodayProgramsFragment.class.getSimpleName();

    private String mDateToday, mSelDate;

    public TodayProgramsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDateToday = getArguments().getString("today_date");
        mSelDate = getArguments().getString("sel_date");

        Log.d(TAG, "Today Date in Fragment:\t" + mDateToday + " && Sel Date in Fragment:\t" + mSelDate);

        if (mSelDate != null && mSelDate != mDateToday){
            // TODO: 10/12/2018 Fetch For That Date Selected
        } else if (mSelDate != null && mSelDate == mDateToday) {
            // TODO: 10/12/2018 Fetch For Today's Date
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_programs, container, false);

        return view;
    }
}
