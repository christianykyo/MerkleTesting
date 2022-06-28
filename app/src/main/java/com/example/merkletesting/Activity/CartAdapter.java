package com.example.merkletesting.Activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merkletesting.Model.Carts.Cart;
import com.example.merkletesting.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Context context;
    private final List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.cartList = carts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_carts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        holder.tvDate.setText(cart.getDate());
        holder.tvId.setText("ID #" + String.valueOf(cart.getId()));
        holder.tvUserId.setText("UserID #" + String.valueOf(cart.getUserId()));

        holder.constraintLayout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            View view = LayoutInflater.from(context).inflate(R.layout.activity_detail_cart, v.findViewById(R.id.layoutDialogContainer));
            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            ((TextView) view.findViewById(R.id.id)).setText("ID #" + String.valueOf(cart.getId()));
            ((TextView) view.findViewById(R.id.userId)).setText("#" + String.valueOf(cart.getUserId()));
            ((TextView) view.findViewById(R.id.date)).setText(cart.getDate());

            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.products);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLayoutManager);
            CartDetailAdapter adapter = new CartDetailAdapter(context, cart.getProducts());
            recyclerView.setAdapter(adapter);
        });

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
        return cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvId, tvUserId;
        ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date);
            tvId = itemView.findViewById(R.id.id);
            tvUserId = itemView.findViewById(R.id.userId);
            constraintLayout = itemView.findViewById(R.id.layout_container);
        }
    }
}