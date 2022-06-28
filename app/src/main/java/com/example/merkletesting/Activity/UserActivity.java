package com.example.merkletesting.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merkletesting.Model.APIEndPoints;
import com.example.merkletesting.Model.Users.User;
import com.example.merkletesting.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.merkletesting.Model.BaseURL.url;

public class UserActivity extends AppCompatActivity {
    private APIEndPoints api;
    private UserAdapter adapter;
    private RecyclerView recyclerView;
    private final List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ImageView back = findViewById(R.id.imageView2);

        recyclerView = findViewById(R.id.users);
        adapter = new UserAdapter(UserActivity.this, users);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(APIEndPoints.class);

        back.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        loadUser();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadUser() {
        Call<List<User>> call = api.users();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();

                if (users.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter = new UserAdapter(UserActivity.this, users);
                    recyclerView.setAdapter(adapter);

                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(UserActivity.this, "Gagal koneksi sistem, silahkan coba lagi...", Toast.LENGTH_LONG).show();
                Log.e("DEBUG", "Error: ", t);
            }
        });
    }

}