package com.example.merkletesting.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merkletesting.Model.Carts.Product;
import com.example.merkletesting.R;

import java.util.List;

public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.ViewHolder> {
    private final Context context;
    private final List<Product> productsList;

    public CartDetailAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productsList = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_detail_carts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product carts = productsList.get(position);

        holder.productsId.setText("ProductId: " + String.valueOf(carts.getProductId()));
        holder.productsQuan.setText("Quantity: " + String.valueOf(carts.getQuantity()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productsId, productsQuan;

        public ViewHolder(View itemView) {
            super(itemView);
            productsId = itemView.findViewById(R.id.productsId);
            productsQuan = itemView.findViewById(R.id.productsQuan);
        }
    }
}