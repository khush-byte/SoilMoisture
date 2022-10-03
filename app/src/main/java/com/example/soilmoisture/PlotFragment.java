package com.example.soilmoisture;

import static com.example.soilmoisture.MainActivity.first_sample_index;
import static com.example.soilmoisture.MainActivity.second_sample_index;
import static com.example.soilmoisture.MainActivity.third_sample_index;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlotFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlotFragment newInstance(String param1, String param2) {
        PlotFragment fragment = new PlotFragment();
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

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plot, container, false);

        Button plot_next = view.findViewById(R.id.plot_next);
        RadioGroup plot_tr1 = view.findViewById(R.id.plot_tr1);
        RadioGroup plot_tr2 = view.findViewById(R.id.plot_tr2);
        RadioGroup plot_tr3 = view.findViewById(R.id.plot_tr3);

        if(first_sample_index==0) ((RadioButton)plot_tr1.getChildAt(0)).setChecked(true);
        if(first_sample_index==1) ((RadioButton)plot_tr1.getChildAt(1)).setChecked(true);
        if(second_sample_index==0) ((RadioButton)plot_tr2.getChildAt(0)).setChecked(true);
        if(second_sample_index==1) ((RadioButton)plot_tr2.getChildAt(1)).setChecked(true);
        if(third_sample_index==0) ((RadioButton)plot_tr3.getChildAt(0)).setChecked(true);
        if(third_sample_index==1) ((RadioButton)plot_tr3.getChildAt(1)).setChecked(true);

        if(first_sample_index!=-1 && second_sample_index!=-1 && third_sample_index!=-1) {
            plot_next.setEnabled(true);
            plot_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
        }else{
            plot_next.setEnabled(false);
            plot_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
        }

        plot_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = plot_tr1.indexOfChild(view.findViewById(plot_tr1.getCheckedRadioButtonId()));
                first_sample_index = index;
                if(index==0) {
                    NavOptions.Builder navBuilder =  new NavOptions.Builder();
                    NavHostFragment.findNavController(PlotFragment.this)
                            .navigate(R.id.sampleFragment, null, navBuilder.build());
                }
            }
        });

        plot_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = plot_tr2.indexOfChild(view.findViewById(plot_tr2.getCheckedRadioButtonId()));
                second_sample_index = index;
                if(index==0) {
                    NavOptions.Builder navBuilder =  new NavOptions.Builder();
                    NavHostFragment.findNavController(PlotFragment.this)
                            .navigate(R.id.sampleFragment, null, navBuilder.build());
                }
            }
        });

        plot_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = plot_tr3.indexOfChild(view.findViewById(plot_tr3.getCheckedRadioButtonId()));
                third_sample_index = index;
                if(index==0) {
                    NavOptions.Builder navBuilder =  new NavOptions.Builder();
                    NavHostFragment.findNavController(PlotFragment.this)
                            .navigate(R.id.sampleFragment, null, navBuilder.build());
                }

                plot_next.setEnabled(true);
                plot_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
            }
        });

        plot_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                NavHostFragment.findNavController(PlotFragment.this)
                        .navigate(R.id.mainFragment, null, navBuilder.build());
            }
        });

        return view;
    }
}