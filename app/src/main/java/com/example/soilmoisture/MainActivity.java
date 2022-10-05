package com.example.soilmoisture;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.soilmoisture.tools.NetworkManager;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class MainActivity extends AppCompatActivity {
    TextView data_field;
    Activity activity;
    public static int first_plot_index = -1;
    public static int second_plot_index = -1;
    public static int third_plot_index = -1;
    //------
    public static int first_sample_plot1_index = -1;
    public static int second_sample_plot1_index = -1;
    public static int third_sample_plot1_index = -1;

    public static int first_sample_plot2_index = -1;
    public static int second_sample_plot2_index = -1;
    public static int third_sample_plot2_index = -1;

    public static int first_sample_plot3_index = -1;
    public static int second_sample_plot3_index = -1;
    public static int third_sample_plot3_index = -1;
    //------
    public static int first_depth_plot1_sample1_index = -1;
    public static int second_depth_plot1_sample1_index = -1;
    public static int third_depth_plot1_sample1_index = -1;

    public static int first_depth_plot1_sample2_index = -1;
    public static int second_depth_plot1_sample2_index = -1;
    public static int third_depth_plot1_sample2_index = -1;

    public static int first_depth_plot1_sample3_index = -1;
    public static int second_depth_plot1_sample3_index = -1;
    public static int third_depth_plot1_sample3_index = -1;

    public static int first_depth_plot2_sample1_index = -1;
    public static int second_depth_plot2_sample1_index = -1;
    public static int third_depth_plot2_sample1_index = -1;

    public static int first_depth_plot2_sample2_index = -1;
    public static int second_depth_plot2_sample2_index = -1;
    public static int third_depth_plot2_sample2_index = -1;

    public static int first_depth_plot2_sample3_index = -1;
    public static int second_depth_plot2_sample3_index = -1;
    public static int third_depth_plot2_sample3_index = -1;

    public static int first_depth_plot3_sample1_index = -1;
    public static int second_depth_plot3_sample1_index = -1;
    public static int third_depth_plot3_sample1_index = -1;

    public static int first_depth_plot3_sample2_index = -1;
    public static int second_depth_plot3_sample2_index = -1;
    public static int third_depth_plot3_sample2_index = -1;

    public static int first_depth_plot3_sample3_index = -1;
    public static int second_depth_plot3_sample3_index = -1;
    public static int third_depth_plot3_sample3_index = -1;
    //------
    public String week_number = "1";
    public static int main_yes_number = 0;
    public static int plot_yes_number = 0;
    public static int plot1_yes_number = 0;
    public static int plot2_yes_number = 0;
    public static int plot3_yes_number = 0;

    public static String date;
    private SharedPreferences.Editor editor;
    String qr_code = "";
    public static String phone_number = "";

    @RequiresApi(api = Build.VERSION_CODES.M)

    @SuppressLint({"SourceLockedOrientationActivity", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.teal_700, this.getTheme()));
        }
        activity = this;
        data_field = findViewById(R.id.data_field);

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            SSLEngine engine = sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        setTime();
        //getRecommendation();

        Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                /*if (!NetworkManager.isNetworkAvailable(getApplicationContext())) {
                    Intent myIntent = new Intent(getApplicationContext(), CheckActivity.class);
                    startActivity(myIntent);
                    activity.finish();
                } else {
                    setTime();
                    handler.postDelayed(this, 1000);
                }*/
                setTime();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(refresh, 1000);

        getJson();

        editor = activity.getApplicationContext().getSharedPreferences("root_data", 0).edit();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("root_data", 0);
        qr_code = pref.getString("login", "");
        if (!pref.getString("week_number", "").equals("")) {
            week_number = pref.getString("week_number", "");
        }
        editor.putString("week_number", week_number);
        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    private void setTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat df2 = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String day = df2.format(c);
        date = df1.format(c);
        String select_date = df3.format(c);
        day = day.substring(0, 1).toUpperCase() + day.substring(1);
        data_field.setText(day + ", " + date);
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(activity, R.id.nav_host_main);
        String fname = Objects.requireNonNull(Objects.requireNonNull(navController.getCurrentDestination()).getLabel()).toString();
        Log.i("MSG2", fname);

        switch (fname) {
            case "fragment_pin":
                activity.finish();
                break;
            case "fragment_info":
                navController.navigate(R.id.pinFragment);
                break;
            case "fragment_main":
                navController.navigate(R.id.pinFragment);
                break;
            case "fragment_first_plot":
                navController.navigate(R.id.mainFragment);
                break;
            case "fragment_plot_info":
                navController.navigate(R.id.mainFragment);
                if (main_yes_number == 1 && plot1_yes_number == 0) first_plot_index = -1;
                else if (main_yes_number == 2 && plot2_yes_number == 0) second_plot_index = -1;
                else if (main_yes_number == 3 && plot3_yes_number == 0) third_plot_index = -1;

                if (first_sample_plot1_index == 1 && second_sample_plot1_index == 1 && third_sample_plot1_index == 1) {
                    first_plot_index = 1;
                }

                if (first_sample_plot2_index == 1 && second_sample_plot2_index == 1 && third_sample_plot2_index == 1) {
                    second_plot_index = 1;
                }

                if (first_sample_plot3_index == 1 && second_sample_plot3_index == 1 && third_sample_plot3_index == 1) {
                    third_plot_index = 1;
                }
                break;
            case "fragment_plot":
                navController.navigate(R.id.plotInfoFragment);
                break;
            case "fragment_sample":
                navController.navigate(R.id.plotFragment);
                break;
            default:
                navController.navigate(R.id.mainFragment);
                break;
        }
    }

    protected void onStop() {
        super.onStop();
        saveData();
    }

    public void saveData() {
        editor.putString("json_data", setJson());
        editor.apply();
    }

    private String setJson() {
        String json_st = "";

        try {
            JSONObject json = new JSONObject();
            json.put("first_plot_index", first_plot_index);
            json.put("second_plot_index", second_plot_index);
            json.put("third_plot_index", third_plot_index);

            json.put("first_sample_plot1_index", first_sample_plot1_index);
            json.put("second_sample_plot1_index", second_sample_plot1_index);
            json.put("third_sample_plot1_index", third_sample_plot1_index);
            json.put("first_sample_plot2_index", first_sample_plot2_index);
            json.put("second_sample_plot2_index", second_sample_plot2_index);
            json.put("third_sample_plot2_index", third_sample_plot2_index);
            json.put("first_sample_plot3_index", first_sample_plot3_index);
            json.put("second_sample_plot3_index", second_sample_plot3_index);
            json.put("third_sample_plot3_index", third_sample_plot3_index);

            json.put("first_depth_plot1_sample1_index", first_depth_plot1_sample1_index);
            json.put("second_depth_plot1_sample1_index", second_depth_plot1_sample1_index);
            json.put("third_depth_plot1_sample1_index", third_depth_plot1_sample1_index);
            json.put("first_depth_plot1_sample2_index", first_depth_plot1_sample2_index);
            json.put("second_depth_plot1_sample2_index", second_depth_plot1_sample2_index);
            json.put("third_depth_plot1_sample2_index", third_depth_plot1_sample2_index);
            json.put("first_depth_plot1_sample3_index", first_depth_plot1_sample3_index);
            json.put("second_depth_plot1_sample3_index", second_depth_plot1_sample3_index);
            json.put("third_depth_plot1_sample3_index", third_depth_plot1_sample3_index);

            json.put("first_depth_plot2_sample1_index", first_depth_plot2_sample1_index);
            json.put("second_depth_plot2_sample1_index", second_depth_plot2_sample1_index);
            json.put("third_depth_plot2_sample1_index", third_depth_plot2_sample1_index);
            json.put("first_depth_plot2_sample2_index", first_depth_plot2_sample2_index);
            json.put("second_depth_plot2_sample2_index", second_depth_plot2_sample2_index);
            json.put("third_depth_plot2_sample2_index", third_depth_plot2_sample2_index);
            json.put("first_depth_plot2_sample3_index", first_depth_plot2_sample3_index);
            json.put("second_depth_plot2_sample3_index", second_depth_plot2_sample3_index);
            json.put("third_depth_plot2_sample3_index", third_depth_plot2_sample3_index);

            json.put("first_depth_plot3_sample1_index", first_depth_plot3_sample1_index);
            json.put("second_depth_plot3_sample1_index", second_depth_plot3_sample1_index);
            json.put("third_depth_plot3_sample1_index", third_depth_plot3_sample1_index);
            json.put("first_depth_plot3_sample2_index", first_depth_plot3_sample2_index);
            json.put("second_depth_plot3_sample2_index", second_depth_plot3_sample2_index);
            json.put("third_depth_plot3_sample2_index", third_depth_plot3_sample2_index);
            json.put("first_depth_plot3_sample3_index", first_depth_plot3_sample3_index);
            json.put("second_depth_plot3_sample3_index", second_depth_plot3_sample3_index);
            json.put("third_depth_plot3_sample3_index", third_depth_plot3_sample3_index);

            json.put("main_yes_number", main_yes_number);
            json.put("plot_yes_number", plot_yes_number);
            json.put("plot1_yes_number", plot1_yes_number);
            json.put("plot2_yes_number", plot2_yes_number);
            json.put("plot3_yes_number", plot3_yes_number);

            json_st = json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json_st;
    }

    private void getJson() {
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("root_data", 0);
        String my_json = pref.getString("json_data", "");

        if (!my_json.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(my_json);
                first_plot_index = jsonObject.getInt("first_plot_index");
                second_plot_index = jsonObject.getInt("second_plot_index");
                third_plot_index = jsonObject.getInt("third_plot_index");

                first_sample_plot1_index = jsonObject.getInt("first_sample_plot1_index");
                second_sample_plot1_index = jsonObject.getInt("second_sample_plot1_index");
                third_sample_plot1_index = jsonObject.getInt("third_sample_plot1_index");
                first_sample_plot2_index = jsonObject.getInt("first_sample_plot2_index");
                second_sample_plot2_index = jsonObject.getInt("second_sample_plot2_index");
                third_sample_plot2_index = jsonObject.getInt("third_sample_plot2_index");
                first_sample_plot3_index = jsonObject.getInt("first_sample_plot3_index");
                second_sample_plot3_index = jsonObject.getInt("second_sample_plot3_index");
                third_sample_plot3_index = jsonObject.getInt("third_sample_plot3_index");

                first_depth_plot1_sample1_index = jsonObject.getInt("first_depth_plot1_sample1_index");
                second_depth_plot1_sample1_index = jsonObject.getInt("second_depth_plot1_sample1_index");
                third_depth_plot1_sample1_index = jsonObject.getInt("third_depth_plot1_sample1_index");
                first_depth_plot1_sample2_index = jsonObject.getInt("first_depth_plot1_sample2_index");
                second_depth_plot1_sample2_index = jsonObject.getInt("second_depth_plot1_sample2_index");
                third_depth_plot1_sample2_index = jsonObject.getInt("third_depth_plot1_sample2_index");
                first_depth_plot1_sample3_index = jsonObject.getInt("first_depth_plot1_sample3_index");
                second_depth_plot1_sample3_index = jsonObject.getInt("second_depth_plot1_sample3_index");
                third_depth_plot1_sample3_index = jsonObject.getInt("third_depth_plot1_sample3_index");

                first_depth_plot2_sample1_index = jsonObject.getInt("first_depth_plot2_sample1_index");
                second_depth_plot2_sample1_index = jsonObject.getInt("second_depth_plot2_sample1_index");
                third_depth_plot2_sample1_index = jsonObject.getInt("third_depth_plot2_sample1_index");
                first_depth_plot2_sample2_index = jsonObject.getInt("first_depth_plot2_sample2_index");
                second_depth_plot2_sample2_index = jsonObject.getInt("second_depth_plot2_sample2_index");
                third_depth_plot2_sample2_index = jsonObject.getInt("third_depth_plot2_sample2_index");
                first_depth_plot2_sample3_index = jsonObject.getInt("first_depth_plot2_sample3_index");
                second_depth_plot2_sample3_index = jsonObject.getInt("second_depth_plot2_sample3_index");
                third_depth_plot2_sample3_index = jsonObject.getInt("third_depth_plot2_sample3_index");

                first_depth_plot3_sample1_index = jsonObject.getInt("first_depth_plot3_sample1_index");
                second_depth_plot3_sample1_index = jsonObject.getInt("second_depth_plot3_sample1_index");
                third_depth_plot3_sample1_index = jsonObject.getInt("third_depth_plot3_sample1_index");
                first_depth_plot3_sample2_index = jsonObject.getInt("first_depth_plot3_sample2_index");
                second_depth_plot3_sample2_index = jsonObject.getInt("second_depth_plot3_sample2_index");
                third_depth_plot3_sample2_index = jsonObject.getInt("third_depth_plot3_sample2_index");
                first_depth_plot3_sample3_index = jsonObject.getInt("first_depth_plot3_sample3_index");
                second_depth_plot3_sample3_index = jsonObject.getInt("second_depth_plot3_sample3_index");
                third_depth_plot3_sample3_index = jsonObject.getInt("third_depth_plot3_sample3_index");

                main_yes_number = jsonObject.getInt("main_yes_number");
                plot_yes_number = jsonObject.getInt("plot_yes_number");
                plot1_yes_number = jsonObject.getInt("plot1_yes_number");
                plot2_yes_number = jsonObject.getInt("plot2_yes_number");
                plot3_yes_number = jsonObject.getInt("plot3_yes_number");

            } catch (JSONException err) {
                Log.d("Error", err.toString());
            }
        }
    }
}
