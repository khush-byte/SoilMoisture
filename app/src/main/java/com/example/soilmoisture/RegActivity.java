package com.example.soilmoisture;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                                SharedPreferences.Editor editor = activity.getApplicationContext().getSharedPreferences("root_data", 0).edit();
                                editor.putString("login", user_qr.getText().toString());
                                editor.putString("pin", user_pinCode1.getText().toString());
                                editor.apply();

                                startActivity(new Intent(activity, MainActivity.class));
                                activity.finish();
                                Toast.makeText(getBaseContext(), "Регистрация прошла успешно!", Toast.LENGTH_LONG).show();
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
}