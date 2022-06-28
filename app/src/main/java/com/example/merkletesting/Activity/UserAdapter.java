package com.example.merkletesting.Activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merkletesting.Model.Users.User;
import com.example.merkletesting.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context context;
    private final List<User> userList;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.userList = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvUsername.setText(user.getUsername());
        holder.tvNamaUser.setText(user.getName().getFirstname() + " " + user.getName().getLastname());

        holder.materialCardView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            View view = LayoutInflater.from(context).inflate(R.layout.activity_detail_user, v.findViewById(R.id.layoutDialogContainer));
            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            ((TextView) view.findViewById(R.id.tvFillNama)).setText(user.getName().getFirstname() + " " + user.getName().getLastname());
            ((TextView) view.findViewById(R.id.tvUserId)).setText("User ID : " + user.getId());
            ((TextView) view.findViewById(R.id.uname)).setText(user.getUsername());
            ((TextView) view.findViewById(R.id.emailUser)).setText(user.getEmail());
            ((TextView) view.findViewById(R.id.address)).setText(user.getAddress().getStreet() + ", " + user.getAddress().getCity());
            ((TextView) view.findViewById(R.id.nomor_ponsel)).setText(user.getPhone());
            ((TextView) view.findViewById(R.id.zipcode)).setText(user.getAddress().getZipcode());

            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
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
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvNamaUser;
        MaterialCardView materialCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.username);
            tvNamaUser = itemView.findViewById(R.id.namaUser);
            materialCardView = itemView.findViewById(R.id.container_user);
        }
    }
}