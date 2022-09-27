package com.example.soilmoisture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.soilmoisture.tools.NetworkManager;

public class CheckActivity extends AppCompatActivity {
    Button connect;
    Activity activity;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        activity = this;
        connect = findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isNetworkAvailable(getApplicationContext())) {
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myIntent);
                    activity.finish();
                } else {
                    Toast.makeText(getBaseContext(), "Нет соединения с интернетом!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}