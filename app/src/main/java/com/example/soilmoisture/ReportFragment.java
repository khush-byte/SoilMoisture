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
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soilmoisture.tools.NetworkManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    NavOptions.Builder navBuilder =  new NavOptions.Builder();
    String qr_code = "";
    String week = "";
    String phone = "";

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

        SharedPreferences pref = view.getContext().getSharedPreferences("root_data", 0);
        qr_code = pref.getString("login", "");
        week = pref.getString("week_number", "");

        report_info.setText("ОТЧЁТ ЗА НЕДЕЛЮ №" + week);
        report_text.setText(myReport());

        report_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ReportFragment.this)
                        .navigate(R.id.mainFragment, null, navBuilder.build());
            }
        });

        report_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkManager.isNetworkAvailable(view.getContext())) {
                    sendData(view);
                }else{
                    NavHostFragment.findNavController(ReportFragment.this)
                            .navigate(R.id.noInternetFragment, null, navBuilder.build());
                }
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

    private void sendData(View v) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String sign = MD5(currentDate + "bCctS9eqoYaZl21a");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://wwcs.tj/meteo/soilsamp/monitoring.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("sign", sign);
                    jsonParam.put("datetime", currentDate);
                    jsonParam.put("qr_code", qr_code);
                    jsonParam.put("week", week);
                    jsonParam.put("phone", phone);

                    jsonParam.put("place1", first_plot_index);
                    jsonParam.put("place1sample1", first_sample_plot1_index);
                    jsonParam.put("place1sample1upto20", first_depth_plot1_sample1_index);
                    jsonParam.put("place1sample1upto40", second_depth_plot1_sample1_index);
                    jsonParam.put("place1sample1upto60", third_depth_plot1_sample1_index);
                    jsonParam.put("place1sample2", second_sample_plot1_index);
                    jsonParam.put("place1sample2upto20", first_depth_plot1_sample2_index);
                    jsonParam.put("place1sample2upto40", second_depth_plot1_sample2_index);
                    jsonParam.put("place1sample2upto60", third_depth_plot1_sample2_index);
                    jsonParam.put("place1sample3", third_sample_plot1_index);
                    jsonParam.put("place1sample3upto20", first_depth_plot1_sample3_index);
                    jsonParam.put("place1sample3upto40", second_depth_plot1_sample3_index);
                    jsonParam.put("place1sample3upto60", third_depth_plot1_sample3_index);

                    jsonParam.put("place2", second_plot_index);
                    jsonParam.put("place2sample1", first_sample_plot2_index);
                    jsonParam.put("place2sample1upto20", first_depth_plot2_sample1_index);
                    jsonParam.put("place2sample1upto40", second_depth_plot2_sample1_index);
                    jsonParam.put("place2sample1upto60", third_depth_plot2_sample1_index);
                    jsonParam.put("place2sample2", second_sample_plot2_index);
                    jsonParam.put("place2sample2upto20", first_depth_plot2_sample2_index);
                    jsonParam.put("place2sample2upto40", second_depth_plot2_sample2_index);
                    jsonParam.put("place2sample2upto60", third_depth_plot2_sample2_index);
                    jsonParam.put("place2sample3", third_sample_plot2_index);
                    jsonParam.put("place2sample3upto20", first_depth_plot2_sample3_index);
                    jsonParam.put("place2sample3upto40", second_depth_plot2_sample3_index);
                    jsonParam.put("place2sample3upto60", third_depth_plot2_sample3_index);

                    jsonParam.put("place3", third_plot_index);
                    jsonParam.put("place3sample1", first_sample_plot3_index);
                    jsonParam.put("place3sample1upto20", first_depth_plot3_sample1_index);
                    jsonParam.put("place3sample1upto40", second_depth_plot3_sample1_index);
                    jsonParam.put("place3sample1upto60", third_depth_plot3_sample1_index);
                    jsonParam.put("place3sample2", second_sample_plot3_index);
                    jsonParam.put("place3sample2upto20", first_depth_plot3_sample2_index);
                    jsonParam.put("place3sample2upto40", second_depth_plot3_sample2_index);
                    jsonParam.put("place3sample2upto60", third_depth_plot3_sample2_index);
                    jsonParam.put("place3sample3", third_sample_plot3_index);
                    jsonParam.put("place3sample3upto20", first_depth_plot3_sample3_index);
                    jsonParam.put("place3sample3upto40", second_depth_plot3_sample3_index);
                    jsonParam.put("place3sample3upto60", third_depth_plot3_sample3_index);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    //Log.i("STATUS", String.valueOf(conn.getResponseCode()));

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();

                    String result = sb.toString();
                    JSONObject obj = new JSONObject(result);
                    Log.i("MSG" , obj.getString("result"));
                    if(obj.getString("result").equals("0")) {
                        NavHostFragment.findNavController(ReportFragment.this)
                                .navigate(R.id.sendInfoFragment, null, navBuilder.build());
                    }else{
                        Toast.makeText(v.getContext(), "Сервер не отвечает! Попробуйте еще раз.", Toast.LENGTH_SHORT).show();
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(v.getContext(), "Сервер не отвечает! Попробуйте еще раз.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        thread.start();
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
        return null;
    }
}