package com.frederico.navigation.activities;

import android.app.ActionBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.frederico.navigation.R;
import com.frederico.navigation.adapters.LoginAdapter;
import com.frederico.navigation.models.LoginHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class Second_Activity extends AppCompatActivity {
    ArrayList<LoginHandler> loginArrayList;
    RecyclerView loginRecycler;
    LoginAdapter loginAdapter;
    AppCompatButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        loginArrayList = (ArrayList<LoginHandler>) getIntent().getSerializableExtra("list");
        loginRecycler = findViewById(R.id.login_recycler);
        backButton = findViewById(R.id.back_button);

        for(LoginHandler l: loginArrayList){
            l.setLoginClickListener((n,a,e)->{
                Toast.makeText(getApplicationContext(), String.format("Name: %s\nAge: %s\nEmail: %s", n,a,e), Toast.LENGTH_SHORT).show();
            });
        }
        loginAdapter = new LoginAdapter(loginArrayList);

        LinearLayoutManager managero = new LinearLayoutManager(getApplicationContext());
        loginRecycler.setLayoutManager(managero);
        loginRecycler.setHasFixedSize(true);
        loginRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        loginRecycler.setAdapter(loginAdapter);


        backButton.setOnClickListener(v->{
            finish();
        });
    }
}