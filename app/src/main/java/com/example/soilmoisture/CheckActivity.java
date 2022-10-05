package com.example.soilmoisture;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Activity activity = this;
        Button check_btn = findViewById(R.id.check_btn);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("root_data", 0);
        String login = pref.getString("login", "");

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                if(login.length()==0){
                    startActivity(new Intent(activity, RegActivity.class));
                }else {
                    startActivity(new Intent(activity, MainActivity.class));
                }
                activity.finish();
            }
        });
    }
}