package com.example.soilmoisture;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.soilmoisture.MainActivity.first_depth_plot1_sample1_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot1_sample2_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot1_sample3_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot2_sample1_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot2_sample2_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot2_sample3_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot3_sample1_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot3_sample2_index;
import static com.example.soilmoisture.MainActivity.first_depth_plot3_sample3_index;
import static com.example.soilmoisture.MainActivity.first_plot_index;
import static com.example.soilmoisture.MainActivity.first_sample_plot1_index;
import static com.example.soilmoisture.MainActivity.first_sample_plot2_index;
import static com.example.soilmoisture.MainActivity.first_sample_plot3_index;
import static com.example.soilmoisture.MainActivity.main_yes_number;
import static com.example.soilmoisture.MainActivity.plot1_yes_number;
import static com.example.soilmoisture.MainActivity.plot2_yes_number;
import static com.example.soilmoisture.MainActivity.plot3_yes_number;
import static com.example.soilmoisture.MainActivity.plot_yes_number;
import static com.example.soilmoisture.MainActivity.second_depth_plot1_sample1_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot1_sample2_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot1_sample3_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot2_sample1_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot2_sample2_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot2_sample3_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot3_sample1_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot3_sample2_index;
import static com.example.soilmoisture.MainActivity.second_depth_plot3_sample3_index;
import static com.example.soilmoisture.MainActivity.second_plot_index;
import static com.example.soilmoisture.MainActivity.second_sample_plot1_index;
import static com.example.soilmoisture.MainActivity.second_sample_plot2_index;
import static com.example.soilmoisture.MainActivity.second_sample_plot3_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample3_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample3_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample3_index;
import static com.example.soilmoisture.MainActivity.third_plot_index;
import static com.example.soilmoisture.MainActivity.third_sample_plot1_index;
import static com.example.soilmoisture.MainActivity.third_sample_plot2_index;
import static com.example.soilmoisture.MainActivity.third_sample_plot3_index;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SendInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendInfoFragment newInstance(String param1, String param2) {
        SendInfoFragment fragment = new SendInfoFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_info, container, false);
        Button send_btn = view.findViewById(R.id.send_btn);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = view.getContext().getSharedPreferences("root_data", 0).edit();
                editor.putString("json_data", "");
                editor.putString("report", "");
                MainActivity activity = (MainActivity) getActivity();
                assert activity != null;
                int next_week = Integer.parseInt(activity.week_number);
                next_week++;
                editor.putString("week_number", String.valueOf(next_week));
                editor.apply();
                activity.week_number = String.valueOf(next_week);

                first_plot_index = -1;
                second_plot_index = -1;
                third_plot_index = -1;
                first_sample_plot1_index = -1;
                second_sample_plot1_index = -1;
                third_sample_plot1_index = -1;
                first_sample_plot2_index = -1;
                second_sample_plot2_index = -1;
                third_sample_plot2_index = -1;
                first_sample_plot3_index = -1;
                second_sample_plot3_index = -1;
                third_sample_plot3_index = -1;
                first_depth_plot1_sample1_index = -1;
                second_depth_plot1_sample1_index = -1;
                third_depth_plot1_sample1_index = -1;
                first_depth_plot1_sample2_index = -1;
                second_depth_plot1_sample2_index = -1;
                third_depth_plot1_sample2_index = -1;
                first_depth_plot1_sample3_index = -1;
                second_depth_plot1_sample3_index = -1;
                third_depth_plot1_sample3_index = -1;
                first_depth_plot2_sample1_index = -1;
                second_depth_plot2_sample1_index = -1;
                third_depth_plot2_sample1_index = -1;
                first_depth_plot2_sample2_index = -1;
                second_depth_plot2_sample2_index = -1;
                third_depth_plot2_sample2_index = -1;
                first_depth_plot2_sample3_index = -1;
                second_depth_plot2_sample3_index = -1;
                third_depth_plot2_sample3_index = -1;
                first_depth_plot3_sample1_index = -1;
                second_depth_plot3_sample1_index = -1;
                third_depth_plot3_sample1_index = -1;
                first_depth_plot3_sample2_index = -1;
                second_depth_plot3_sample2_index = -1;
                third_depth_plot3_sample2_index = -1;
                first_depth_plot3_sample3_index = -1;
                second_depth_plot3_sample3_index = -1;
                third_depth_plot3_sample3_index = -1;
                main_yes_number = 0;
                plot_yes_number = 0;
                plot1_yes_number = 0;
                plot2_yes_number = 0;
                plot3_yes_number = 0;

                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                navBuilder.setExitAnim(R.anim.exit).setEnterAnim(R.anim.enter);
                NavHostFragment.findNavController(SendInfoFragment.this)
                        .navigate(R.id.infoFragment, null, navBuilder.build());
            }
        });
        return view;
    }
}