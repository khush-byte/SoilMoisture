package com.example.soilmoisture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Activity activity = this;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("root_data", 0);
        String login = pref.getString("login", "");

        ImageView imageView = findViewById(R.id.imageView); // Declare an imageView to show the animation.
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(login.length()==0){
                    startActivity(new Intent(activity, RegActivity.class));
                }else {
                    startActivity(new Intent(activity, MainActivity.class));
                }
                activity.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(anim);
    }
}