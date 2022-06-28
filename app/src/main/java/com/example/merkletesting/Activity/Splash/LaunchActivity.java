package com.example.merkletesting.Activity.Splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.example.merkletesting.Activity.HomeActivity;
import com.example.merkletesting.Activity.LoginActivity;
import com.example.merkletesting.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ImageView logo = findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String username = getSharedPreferences("myprefs", Context.MODE_PRIVATE).getString("username", null);

                if  (username != null) {

                    Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else {

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(LaunchActivity.this, logo, ViewCompat.getTransitionName(logo));
                    Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(intent, options.toBundle());
                }
            }
        }, 2000);
    }
}
