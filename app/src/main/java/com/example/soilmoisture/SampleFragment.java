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
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot1_sample3_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot2_sample3_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample1_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample2_index;
import static com.example.soilmoisture.MainActivity.third_depth_plot3_sample3_index;

import android.annotation.SuppressLint;
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
 * Use the {@link SampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SampleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SampleFragment newInstance(String param1, String param2) {
        SampleFragment fragment = new SampleFragment();
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
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        Button sample_next = view.findViewById(R.id.sample_next);
        RadioGroup sample_tr1 = view.findViewById(R.id.sample_tr1);
        RadioGroup sample_tr2 = view.findViewById(R.id.sample_tr2);
        RadioGroup sample_tr3 = view.findViewById(R.id.sample_tr3);
        TextView sample_text = view.findViewById(R.id.sample_text);

        if(main_yes_number==1) {
            if(plot_yes_number==1) sample_text.setText("УЧАСТОК №1. В ЯМЕ");
            else if(plot_yes_number==2) sample_text.setText("УЧАСТОК №1. СВЕРХУ ОТ ЯМЫ");
            else if(plot_yes_number==3) sample_text.setText("УЧАСТОК №1. ПРОМЕЖУТОК");
        }
        else if(main_yes_number==2) {
            if(plot_yes_number==1) sample_text.setText("УЧАСТОК №2. В ЯМЕ");
            else if(plot_yes_number==2) sample_text.setText("УЧАСТОК №2. СВЕРХУ ОТ ЯМЫ");
            else if(plot_yes_number==3) sample_text.setText("УЧАСТОК №2. ПРОМЕЖУТОК");
        }
        else if(main_yes_number==3) {
            if(plot_yes_number==1) sample_text.setText("УЧАСТОК №3. В ЯМЕ");
            else if(plot_yes_number==2) sample_text.setText("УЧАСТОК №3. СВЕРХУ ОТ ЯМЫ");
            else if(plot_yes_number==3) sample_text.setText("УЧАСТОК №3. ПРОМЕЖУТОК");
        }

        if(main_yes_number == 1) {
            if (plot_yes_number == 1) {
                if (first_depth_plot1_sample1_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot1_sample1_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot1_sample1_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot1_sample1_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot1_sample1_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot1_sample1_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot1_sample1_index != -1 && second_depth_plot1_sample1_index != -1 && third_depth_plot1_sample1_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot1_sample1_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample1_index!=-1 && second_depth_plot1_sample1_index!=-1 && third_depth_plot1_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot1_sample1_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample1_index!=-1 && second_depth_plot1_sample1_index!=-1 && third_depth_plot1_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot1_sample1_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample1_index!=-1 && second_depth_plot1_sample1_index!=-1 && third_depth_plot1_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 2) {
                if (first_depth_plot1_sample2_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot1_sample2_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot1_sample2_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot1_sample2_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot1_sample2_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot1_sample2_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot1_sample2_index != -1 && second_depth_plot1_sample2_index != -1 && third_depth_plot1_sample2_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot1_sample2_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample2_index!=-1 && second_depth_plot1_sample2_index!=-1 && third_depth_plot1_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot1_sample2_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample2_index!=-1 && second_depth_plot1_sample2_index!=-1 && third_depth_plot1_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot1_sample2_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample2_index!=-1 && second_depth_plot1_sample2_index!=-1 && third_depth_plot1_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 3) {
                if (first_depth_plot1_sample3_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot1_sample3_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot1_sample3_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot1_sample3_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot1_sample3_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot1_sample3_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot1_sample3_index != -1 && second_depth_plot1_sample3_index != -1 && third_depth_plot1_sample3_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot1_sample3_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample3_index!=-1 && second_depth_plot1_sample3_index!=-1 && third_depth_plot1_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot1_sample3_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample3_index!=-1 && second_depth_plot1_sample3_index!=-1 && third_depth_plot1_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot1_sample3_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot1_sample3_index!=-1 && second_depth_plot1_sample3_index!=-1 && third_depth_plot1_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            }
        }
        else if(main_yes_number == 2) {
            if (plot_yes_number == 1) {
                if (first_depth_plot2_sample1_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot2_sample1_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot2_sample1_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot2_sample1_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot2_sample1_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot2_sample1_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot2_sample1_index != -1 && second_depth_plot2_sample1_index != -1 && third_depth_plot2_sample1_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot2_sample1_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample1_index!=-1 && second_depth_plot2_sample1_index!=-1 && third_depth_plot2_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot2_sample1_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample1_index!=-1 && second_depth_plot2_sample1_index!=-1 && third_depth_plot2_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot2_sample1_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample1_index!=-1 && second_depth_plot2_sample1_index!=-1 && third_depth_plot2_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 2) {
                if (first_depth_plot2_sample2_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot2_sample2_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot2_sample2_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot2_sample2_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot2_sample2_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot2_sample2_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot2_sample2_index != -1 && second_depth_plot2_sample2_index != -1 && third_depth_plot2_sample2_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot2_sample2_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample2_index!=-1 && second_depth_plot2_sample2_index!=-1 && third_depth_plot2_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot2_sample2_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample2_index!=-1 && second_depth_plot2_sample2_index!=-1 && third_depth_plot2_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot2_sample2_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample2_index!=-1 && second_depth_plot2_sample2_index!=-1 && third_depth_plot2_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 3) {
                if (first_depth_plot2_sample3_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot2_sample3_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot2_sample3_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot2_sample3_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot2_sample3_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot2_sample3_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot2_sample3_index != -1 && second_depth_plot2_sample3_index != -1 && third_depth_plot2_sample3_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot2_sample3_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample3_index!=-1 && second_depth_plot2_sample3_index!=-1 && third_depth_plot2_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot2_sample3_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample3_index!=-1 && second_depth_plot2_sample3_index!=-1 && third_depth_plot2_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot2_sample3_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot2_sample3_index!=-1 && second_depth_plot2_sample3_index!=-1 && third_depth_plot2_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            }
        }
        else if(main_yes_number == 3) {
            if (plot_yes_number == 1) {
                if (first_depth_plot3_sample1_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot3_sample1_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot3_sample1_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot3_sample1_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot3_sample1_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot3_sample1_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot3_sample1_index != -1 && second_depth_plot3_sample1_index != -1 && third_depth_plot3_sample1_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot3_sample1_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample1_index!=-1 && second_depth_plot3_sample1_index!=-1 && third_depth_plot3_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot3_sample1_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample1_index!=-1 && second_depth_plot3_sample1_index!=-1 && third_depth_plot3_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot3_sample1_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample1_index!=-1 && second_depth_plot3_sample1_index!=-1 && third_depth_plot3_sample1_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 2) {
                if (first_depth_plot3_sample2_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot3_sample2_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot3_sample2_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot3_sample2_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot3_sample2_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot3_sample2_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot3_sample2_index != -1 && second_depth_plot3_sample2_index != -1 && third_depth_plot3_sample2_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot3_sample2_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample2_index!=-1 && second_depth_plot3_sample2_index!=-1 && third_depth_plot3_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot3_sample2_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample2_index!=-1 && second_depth_plot3_sample2_index!=-1 && third_depth_plot3_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot3_sample2_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample2_index!=-1 && second_depth_plot3_sample2_index!=-1 && third_depth_plot3_sample2_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            } else if (plot_yes_number == 3) {
                if (first_depth_plot3_sample3_index == 0)
                    ((RadioButton) sample_tr1.getChildAt(0)).setChecked(true);
                if (first_depth_plot3_sample3_index == 1)
                    ((RadioButton) sample_tr1.getChildAt(1)).setChecked(true);
                if (second_depth_plot3_sample3_index == 0)
                    ((RadioButton) sample_tr2.getChildAt(0)).setChecked(true);
                if (second_depth_plot3_sample3_index == 1)
                    ((RadioButton) sample_tr2.getChildAt(1)).setChecked(true);
                if (third_depth_plot3_sample3_index == 0)
                    ((RadioButton) sample_tr3.getChildAt(0)).setChecked(true);
                if (third_depth_plot3_sample3_index == 1)
                    ((RadioButton) sample_tr3.getChildAt(1)).setChecked(true);

                if (first_depth_plot3_sample3_index != -1 && second_depth_plot3_sample3_index != -1 && third_depth_plot3_sample3_index != -1) {
                    sample_next.setEnabled(true);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                } else {
                    sample_next.setEnabled(false);
                    sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.grey));
                }

                sample_tr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        first_depth_plot3_sample3_index= sample_tr1.indexOfChild(view.findViewById(sample_tr1.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample3_index!=-1 && second_depth_plot3_sample3_index!=-1 && third_depth_plot3_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        second_depth_plot3_sample3_index = sample_tr2.indexOfChild(view.findViewById(sample_tr2.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample3_index!=-1 && second_depth_plot3_sample3_index!=-1 && third_depth_plot3_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });

                sample_tr3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @SuppressLint("UseCompatLoadingForColorStateLists")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        third_depth_plot3_sample3_index = sample_tr3.indexOfChild(view.findViewById(sample_tr3.getCheckedRadioButtonId()));
                        if(first_depth_plot3_sample3_index!=-1 && second_depth_plot3_sample3_index!=-1 && third_depth_plot3_sample3_index!=-1) {
                            sample_next.setEnabled(true);
                            sample_next.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.teal_700));
                        }
                    }
                });
            }
        }

        sample_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder =  new NavOptions.Builder();
                NavHostFragment.findNavController(SampleFragment.this)
                        .navigate(R.id.plotFragment, null, navBuilder.build());
            }
        });

        return view;
    }
}