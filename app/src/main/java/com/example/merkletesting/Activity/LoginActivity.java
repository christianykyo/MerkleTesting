package com.example.merkletesting.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.merkletesting.Model.Users.User;
import com.example.merkletesting.R;
import com.example.merkletesting.Model.APIEndPoints;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.merkletesting.Model.BaseURL.url;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout textInputLayout2;
    private EditText edtUsername, edtPassword;
    private APIEndPoints api;
    private SharedPreferences sharedprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedprefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(APIEndPoints.class);

        edtPassword = findViewById(R.id.login_Pass);
        edtUsername = findViewById(R.id.login_Username);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        MaterialButton btnLogin = findViewById(R.id.signin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    private void validateForm() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            edtUsername.setError("Username Kurang, silahkan coba lagi...");
        } else if (password.isEmpty()) {
            edtPassword.setError("Password Kurang, silahkan coba lagi...");
            textInputLayout2.setEndIconVisible(false);

            edtPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    textInputLayout2.setEndIconVisible(true);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        } else {
            login(username, password);
        }
    }

    private void login(String username, String password) {
        Call<User> call = api.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (user != null) {
                            sharedprefs.edit().putString("username", username).apply();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);

                        } else {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(LoginActivity.this, "Username atau Password salah, silahkan coba lagi...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(LoginActivity.this, "Gagal koneksi sistem, silahkan coba lagi...", Toast.LENGTH_LONG).show();
                Log.e("DEBUG", "Error: ", t);
            }
        });
    }
}