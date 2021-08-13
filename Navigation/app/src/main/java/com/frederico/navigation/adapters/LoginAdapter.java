package com.frederico.navigation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frederico.navigation.R;
import com.frederico.navigation.activities.Second_Activity;
import com.frederico.navigation.models.LoginHandler;

import java.util.List;
import java.util.zip.Inflater;

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginHolder> {
    List<LoginHandler> loginList;

    public LoginAdapter(List<LoginHandler> login_list){
        loginList = login_list;
    }
    @Override
    public LoginHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View loginView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.login_card_list_1,
                parent,
                false
        );
        return new LoginHolder(loginView);
    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    @Override
    public void onBindViewHolder(LoginHolder holder, int position) {
        String name = loginList.get(position).getName();
        String age = loginList.get(position).getAge();
        String email = loginList.get(position).getEmail();
        holder.Name.setText(name);
        holder.Age.setText(age);
        holder.Email.setText(email);
        holder.itemView.setOnClickListener(v ->{
            loginList.get(position).getLoginClickListener().click(name, age, email);
        });
    }



    public static class LoginHolder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Age;
        TextView Email;
        public LoginHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name_card_login);
            Age = itemView.findViewById(R.id.age_card_login);
            Email = itemView.findViewById(R.id.email_card_login);
        }
    }
}
