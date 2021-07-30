package com.frederico.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    AppCompatButton confirmButton;
    TextInputLayout nameInput;
    TextInputLayout ageInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.text_edit_name);
        ageInput = findViewById(R.id.text_edit_age);
        confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Second_Activity.class);
            intent.putExtra("Name",nameInput.getEditText().getText().toString());
            intent.putExtra("Age", ageInput.getEditText().getText().toString());
            startActivity(intent);
        });
    }
}