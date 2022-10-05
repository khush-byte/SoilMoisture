package com.example.soilmoisture;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        Button info_start = view.findViewById(R.id.info_start);
        ConstraintLayout new_week_info = view.findViewById(R.id.new_week_info);

        SharedPreferences pref = view.getContext().getSharedPreferences("root_data", 0);
        String report = pref.getString("report", "");
        String week = pref.getString("week_number", "");

        if(report.equals("done")){
            NavOptions.Builder navBuilder =  new NavOptions.Builder();
            NavHostFragment.findNavController(InfoFragment.this)
                    .navigate(R.id.reportFragment, null, navBuilder.build());
        }

        if(!week.equals("1")) new_week_info.setVisibility(View.VISIBLE);
        else new_week_info.setVisibility(View.GONE);

        info_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                NavHostFragment.findNavController(InfoFragment.this)
                        .navigate(R.id.mainFragment, null, navBuilder.build());
            }
        });

        return view;
    }
}