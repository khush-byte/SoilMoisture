package com.example.soilmoisture;

import static com.example.soilmoisture.MainActivity.first_plot_index;
import static com.example.soilmoisture.MainActivity.second_plot_index;
import static com.example.soilmoisture.MainActivity.third_plot_index;
import static com.example.soilmoisture.MainActivity.main_yes_number;

import android.annotation.SuppressLint;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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

    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button submit = view.findViewById(R.id.submit);
        RadioGroup main_tr1 = view.findViewById(R.id.main_tr1);
        RadioGroup main_tr2 = view.findViewById(R.id.main_tr2);
        RadioGroup main_tr3 = view.findViewById(R.id.main_tr3);
        TextView info_text = view.findViewById(R.id.info_text);
        RadioButton main_yes1 = view.findViewById(R.id.main_yes1);
        RadioButton main_yes2 = view.findViewById(R.id.main_yes2);
        RadioButton main_yes3 = view.findViewById(R.id.main_yes3);

        if(first_plot_index==0) ((RadioButton)main_tr1.getChildAt(0)).setChecked(true);
        if(first_plot_index==1) ((RadioButton)main_tr1.getChildAt(1)).setChecked(true);
        if(second_plot_index==0) ((RadioButton)main_tr2.getChildAt(0)).setChecked(true);
        if(second_plot_index==1) ((RadioButton)main_tr2.getChildAt(1)).setChecked(true);
        if(third_plot_index==0) ((RadioButton)main_tr3.getChildAt(0)).setChecked(true);
        if(third_plot_index==1) ((RadioButton)main_tr3.getChildAt(1)).setChecked(true);

        if(first_plot_index!=-1 && second_plot_index!=-1 && third_plot_index!=-1) {
            submit.setEnabled(true);
            submit.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
        }else{
            submit.setEnabled(false);
            submit.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
        }

        SharedPreferences pref = view.getContext().getSharedPreferences("root_data", 0);
        String week_number = pref.getString("week_number", "");
        info_text.setText("НЕДЕЛЯ №"+week_number);

        main_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = main_tr1.indexOfChild(view.findViewById(main_tr1.getCheckedRadioButtonId()));
                first_plot_index = index;
                if(first_plot_index!=-1 && second_plot_index!=-1 && third_plot_index!=-1) {
                    submit.setEnabled(true);
                    submit.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                }
            }
        });

        main_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = main_tr2.indexOfChild(view.findViewById(main_tr2.getCheckedRadioButtonId()));
                second_plot_index = index;
                if(first_plot_index!=-1 && second_plot_index!=-1 && third_plot_index!=-1) {
                    submit.setEnabled(true);
                    submit.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                }
            }
        });

        main_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = main_tr3.indexOfChild(view.findViewById(main_tr3.getCheckedRadioButtonId()));
                third_plot_index = index;
                if(first_plot_index!=-1 && second_plot_index!=-1 && third_plot_index!=-1) {
                    submit.setEnabled(true);
                    submit.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                }
            }
        });

        openPage(main_yes1, 1);
        openPage(main_yes2, 2);
        openPage(main_yes3, 3);

        return view;
    }

    private void openPage(RadioButton btn, int number){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.plotInfoFragment, null, navBuilder.build());
                main_yes_number = number;
            }
        });
    }
}