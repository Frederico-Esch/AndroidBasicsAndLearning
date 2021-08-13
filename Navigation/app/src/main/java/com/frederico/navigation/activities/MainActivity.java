package com.frederico.navigation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.frederico.navigation.R;
import com.frederico.navigation.models.LoginHandler;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;

import static java.util.Objects.isNull;

public class MainActivity extends AppCompatActivity {
    AppCompatButton confirmButton;
    AppCompatButton seeListButton;
    TextInputLayout nameInput;
    TextInputLayout ageInput;
    TextInputLayout emailInput;
    ArrayList<LoginHandler> loginList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.text_edit_name);
        ageInput = findViewById(R.id.text_edit_age);
        emailInput = findViewById(R.id.text_edit_email);
        confirmButton = findViewById(R.id.confirm_button);
        seeListButton = findViewById(R.id.see_list_button);

        confirmButton.setOnClickListener(v -> {
            String name = nameInput.getEditText().getText().toString().trim();
            String age  = ageInput.getEditText().getText().toString().trim();
            String email = emailInput.getEditText().getText().toString().trim();
            if(validate(name, age, email)){
                Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show();
                loginList.add(new LoginHandler(name, age, email));
            }else{
                Toast.makeText(this, "Não Cadastrado", Toast.LENGTH_SHORT).show();
            }
        });

        seeListButton.setOnClickListener(v ->{
            Intent intent = new Intent(this, Second_Activity.class);
            intent.putExtra("list",  (Serializable) loginList);
            startActivity(intent);
        });
    }
    boolean validate(String name, String age, String email){
        if(name.equals("")){
            nameInput.setError(getString(R.string.invalid_text_input));
        }else{
            nameInput.setError("");
        }
        if(age.equals("")){
            ageInput.setError(getString(R.string.invalid_text_input));
        }else{
            ageInput.setError("");
        }
        if(email.matches("[\\@\\#\\%\\$\\&\\*\\-\\=\\+\\\"\\!\\\'\\.\\w]+@[\\w]+(\\.[a-z]+)+")){
            emailInput.setError("");
        }else{
            emailInput.setError(getString(R.string.invalid_text_input));
        }
        if(isNull(nameInput.getError()) && isNull(ageInput.getError()) && isNull(emailInput.getError())){
            return true;
        }
        return false;
    }
}