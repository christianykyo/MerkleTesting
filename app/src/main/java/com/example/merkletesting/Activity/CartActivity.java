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

import com.example.merkletesting.Model.Carts.Cart;
import com.example.merkletesting.R;
import com.example.merkletesting.Model.APIEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.merkletesting.Model.BaseURL.url;

public class CartActivity extends AppCompatActivity {
    private APIEndPoints api;
    private CartAdapter adapter;
    private RecyclerView recyclerView;
    private final List<Cart> carts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);

        ImageView back = findViewById(R.id.imageView2);

        recyclerView = findViewById(R.id.carts);
        adapter = new CartAdapter(CartActivity.this, carts);
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
        loadCart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void loadCart() {
        Call<List<Cart>> call = api.carts();
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> carts = response.body();

                if (carts.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter = new CartAdapter(CartActivity.this, carts);
                    recyclerView.setAdapter(adapter);

                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(CartActivity.this, "Gagal koneksi sistem, silahkan coba lagi...", Toast.LENGTH_LONG).show();
                Log.e("DEBUG", "Error: ", t);
            }
        });
    }
}