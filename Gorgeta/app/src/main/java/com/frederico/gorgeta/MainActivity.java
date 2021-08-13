package com.frederico.gorgeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextInputEditText dateText;
    //DatePickerDialog.OnDateSetListener dateListener;
    TextInputLayout emailText;
    TextInputLayout nameText;
    AppCompatButton sendButton;
    TextView resText;
    CheckBox agreeCheck, receiveECheck;
    AppCompatButton clearBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateText = findViewById(R.id.textDateBox);
        dateText.setKeyListener(null);
        nameText = findViewById(R.id.textName);
        emailText =  findViewById(R.id.textEmail);
        sendButton = findViewById(R.id.sendBut);
        resText = findViewById(R.id.resText);
        agreeCheck = findViewById(R.id.agree);
        receiveECheck = findViewById(R.id.receiveE);
        clearBut = findViewById(R.id.clearBut);

        dateText.setOnClickListener(this::clickData);

        nameText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().matches("[A-z]+([ ][A-z]+)*")){
                   nameText.setError(null);
                }else{
                    nameText.setError(getString(R.string.Error_nome));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        emailText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().matches("[A-z0-9\\.\\w\\-\\$\\%\\&\\#\\!\\+\\=]+\\@[\\w]+(\\.[\\w]+)+")){
                    emailText.setError(null);
                }else{
                    emailText.setError(getString(R.string.Error_email));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sendButton.setOnClickListener(v ->{
            if(nameText.getError() == null
                    && !dateText.getText().toString().equals("")
                    && emailText.getError() == null
                    && agreeCheck.isChecked()) {
                resText.setText(String.format("Nome: %s\nData: %s\nEmail: %s\n%s",
                        nameText.getEditText().getText(),
                        dateText.getText(),
                        emailText.getEditText().getText(),
                        (receiveECheck.isChecked()) ? getString(R.string.will_receiveE) : ""));
            }else{
                Toast.makeText(MainActivity.this, getString(R.string.error_send), Toast.LENGTH_SHORT).show();
            }
        });

        clearBut.setOnClickListener(v->{
            AlertDialog.Builder clearAlert = new AlertDialog.Builder(MainActivity.this);
            clearAlert.setCancelable(false);
            clearAlert.setTitle(R.string.clear_dialog_title);
            clearAlert.setIcon(R.drawable.warning_24);
            clearAlert.setMessage(R.string.clear_dialog_message);
            clearAlert.setPositiveButton(R.string.Yes, this::clearEveryBox);
            clearAlert.setNegativeButton(R.string.No,
                    (d,w)-> Toast.makeText(getApplicationContext(), R.string.clearTextCancelled, Toast.LENGTH_SHORT).show());
            clearAlert.show();
            /*
            nameText.getEditText().setText(null);
            nameText.setError(null);
            emailText.getEditText().setText(null);
            emailText.setError(null);
            dateText.setText(null);
            agreeCheck.setChecked(false);
            receiveECheck.setChecked(false);
            resText.setText(R.string.placeHolderMainAct);
            Toast.makeText(getApplicationContext(), R.string.clearedText, Toast.LENGTH_SHORT).show();
             */
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void clickData(View v){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                R.style.Theme_Gorgeta_DialogDate,
                (view, yearRef, monthRef, dayOfMonth) ->{
                    monthRef += 1;
                    String date = dayOfMonth + "/" + monthRef + "/" + yearRef;
                    dateText.setText(date);
                },
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.color.transparent));
        dialog.show();
    }
    public void clearEveryBox(DialogInterface d, int w){
        nameText.getEditText().setText(null);
        nameText.setError(null);
        emailText.getEditText().setText(null);
        emailText.setError(null);
        dateText.setText(null);
        agreeCheck.setChecked(false);
        receiveECheck.setChecked(false);
        resText.setText(R.string.placeHolderMainAct);
        Toast.makeText(getApplicationContext(), R.string.clearedText, Toast.LENGTH_SHORT).show();
    }
}