package com.example.soilmoisture;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegActivity extends AppCompatActivity {

    private EditText user_qr, user_pinCode1, user_pinCode2;
    private Button start_btn;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        user_qr = findViewById(R.id.user_qr);
        user_pinCode1 = findViewById(R.id.user_pinCode1);
        user_pinCode2 = findViewById(R.id.user_pinCode2);
        start_btn = findViewById(R.id.reg_btn);
        activity = this;

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!user_qr.getText().toString().equals("") && !user_pinCode1.getText().toString().equals("") && !user_pinCode1.getText().toString().equals("")) {
                    if (user_pinCode1.getText().toString().length() < 4) {
                        Toast.makeText(getBaseContext(), "ПИН код должен состоять из 4-х цифр!", Toast.LENGTH_LONG).show();
                    } else {
                        if (user_pinCode1.getText().toString().equals(user_pinCode2.getText().toString())) {
                            if(user_qr.getText().length() < 5) {
                                Toast.makeText(getBaseContext(), "QR код должен состоять из 5-ти цифр!", Toast.LENGTH_LONG).show();
                            } else {
                                if (NetworkManager.isNetworkAvailable(getApplicationContext())) {
                                    regUser(user_qr.getText().toString());
                                }else{
                                    startActivity(new Intent(activity, CheckActivity.class));
                                    activity.finish();
                                }
                            }
                        }else{
                            Toast.makeText(getBaseContext(), "Оба ПИН кода должны совподать!", Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Пожалуйста, заполните все поля!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void regUser(String qr) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String sign = MD5(currentDate + "bCctS9eqoYaZl21a");

        Thread thread = new Thread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    URL url = new URL("https://wwcs.tj/meteo/soilsamp/reg.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("sign", sign);
                    jsonParam.put("datetime", currentDate);
                    jsonParam.put("qr", qr);

                    //Log.i("JSON", jsonParam.toString());
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
                    conn.disconnect();

                    String result = sb.toString();
                    JSONObject obj = new JSONObject(result);

                    if(obj.getString("result").equals("0")){
                        Log.i("Debug", "YES");
                        SharedPreferences.Editor editor = activity.getApplicationContext().getSharedPreferences("root_data", 0).edit();
                        editor.putString("login", user_qr.getText().toString());
                        editor.putString("pin", user_pinCode1.getText().toString());
                        editor.apply();

                        startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                final Toast toast = Toast.makeText(getApplicationContext(), "Регистрация прошла успешно!", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            public void run() {
                                final Toast toast = Toast.makeText(getApplicationContext(), "QR-код недействителен!", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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