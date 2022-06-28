package com.example.merkletesting.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.merkletesting.R;
import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences sharedprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedprefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);

        TextView nama = findViewById(R.id.nama);
        nama.setText(getSharedPreferences("myprefs", Context.MODE_PRIVATE).getString("username", null));

        MaterialButton logout = findViewById(R.id.logoutAdmin);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);

        constraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserActivity.class);
            startActivity(intent);
        });

        constraintLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            sharedprefs.edit().clear().apply();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda yakin ingin keluar dari akun ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sharedprefs.edit().clear().apply();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}