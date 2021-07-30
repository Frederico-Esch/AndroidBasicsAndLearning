package com.frederico.navigation;

import android.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {
    TextView nameTextView;
    TextView ageTextView;
    AppCompatButton backButton;
    //ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameTextView = findViewById(R.id.name_end_point);
        ageTextView = findViewById(R.id.age_end_point);
        backButton = findViewById(R.id.back_button);

        String name = getIntent().getExtras().getString("Name").trim();
        String age = getIntent().getExtras().getString("Age");
        if(name.equals("") || age.equals("")){
            name = "Nome ou idade não informado";
            age = "";
        }

        nameTextView.setText(name);
        ageTextView.setText(age);

        backButton.setOnClickListener(v->{
            finish();
        });
    }
}