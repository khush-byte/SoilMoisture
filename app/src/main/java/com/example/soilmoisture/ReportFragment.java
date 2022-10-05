package com.example.soilmoisture;

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

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        TextView report_info = view.findViewById(R.id.report_info);
        TextView report_text = view.findViewById(R.id.report_text);
        Button report_back = view.findViewById(R.id.report_back);
        Button report_next = view.findViewById(R.id.report_next);

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        report_info.setText("ОТЧЁТ ЗА НЕДЕЛЮ №"+activity.week_number);

        report_text.setText(myReport());

        report_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                NavHostFragment.findNavController(ReportFragment.this)
                        .navigate(R.id.mainFragment, null, navBuilder.build());
            }
        });

        report_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private String myReport(){
        String text = "Образцы почв взяты из следующих мест:\n";

        if(first_plot_index==0) {
            text += "\nУчасток №1 - ДА\n";

            if(first_sample_plot1_index==0){
                text += "\t\t\t\t - в яме - ДА\n";

                if(first_depth_plot1_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot1_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot1_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - в яме - НЕТ\n";
            }

            if(second_sample_plot1_index==0){
                text += "\t\t\t\t - сверху от ямы - ДА\n";

                if(first_depth_plot1_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot1_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot1_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - сверху от ямы - НЕТ\n";
            }

            if(third_sample_plot1_index==0){
                text += "\t\t\t\t - промежуток - ДА\n";

                if(first_depth_plot1_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot1_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot1_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - промежуток - НЕТ\n";
            }
        }else{
            text += "\nУчасток №1 - НЕТ\n";
        }

        if(second_plot_index==0) {
            text += "\nУчасток №2 - ДА\n";

            if(first_sample_plot2_index==0){
                text += "\t\t\t\t - в яме - ДА\n";

                if(first_depth_plot2_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot2_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot2_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - в яме - НЕТ\n";
            }

            if(second_sample_plot2_index==0){
                text += "\t\t\t\t - сверху от ямы - ДА\n";

                if(first_depth_plot2_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot2_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot2_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - сверху от ямы - НЕТ\n";
            }

            if(third_sample_plot2_index==0){
                text += "\t\t\t\t - промежуток - ДА\n";

                if(first_depth_plot2_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot2_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot2_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - промежуток - НЕТ\n";
            }
        }
        else{
            text += "\nУчасток №2 - НЕТ\n";
        }

        if(third_plot_index==0) {
            text += "\nУчасток №3 - ДА\n";

            if(first_sample_plot3_index==0){
                text += "\t\t\t\t - в яме - ДА\n";

                if(first_depth_plot3_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot3_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot3_sample1_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - в яме - НЕТ\n";
            }

            if(second_sample_plot3_index==0){
                text += "\t\t\t\t - сверху от ямы - ДА\n";

                if(first_depth_plot3_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot3_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot3_sample2_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - сверху от ямы - НЕТ\n";
            }

            if(third_sample_plot3_index==0){
                text += "\t\t\t\t - промежуток - ДА\n";

                if(first_depth_plot3_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 0 до 20 см. - НЕТ\n";
                }

                if(second_depth_plot3_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 20 до 40 см. - НЕТ\n";
                }

                if(third_depth_plot3_sample3_index==0){
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - ДА\n";

                }else{
                    text += "\t\t\t\t\t\t\t\t - глубина от 40 до 60 см. - НЕТ\n";
                }

            }else{
                text += "\t\t\t\t - промежуток - НЕТ\n";
            }
        }
        else{
            text += "\nУчасток №3 - НЕТ\n";
        }

        return text;
    }
}