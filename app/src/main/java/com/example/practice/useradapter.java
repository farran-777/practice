package com.example.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class useradapter extends RecyclerView.Adapter<useradapter.UserViewHolder> {
    private List<modeluser> userList;

    public useradapter(List<modeluser> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sign_up, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView passwordTextView;
        public TextView cpasswordTextView;
        public TextView emailTextView;

        public TextView usernameTextview;


        public UserViewHolder(View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.email);
            passwordTextView = itemView.findViewById(R.id.password);
            cpasswordTextView = itemView.findViewById(R.id.password);
            usernameTextview = itemView.findViewById(R.id.username);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        modeluser currentUser = userList.get(position);
        holder.emailTextView.setText(currentUser.email);
        holder.passwordTextView.setText(currentUser.password);
        holder.cpasswordTextView.setText(currentUser.cpass);
        holder.usernameTextview.setText(currentUser.username);

    }



}