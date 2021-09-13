package com.frederico.listas.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.frederico.listas.R;
import com.google.android.material.textfield.TextInputLayout;

public class EditCreate extends AppCompatActivity {
    int ID;
    Toolbar toolbar;
    TextInputLayout atividadeInput;
    SQLiteDatabase banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_create);
        atividadeInput = findViewById(R.id.atividade_input);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        banco = openOrCreateDatabase("lista", MODE_PRIVATE, null);

        ID = getIntent().getIntExtra("ID", 0);
        if(ID == 0){
            getSupportActionBar().setTitle("Criar");
        }else{
            getSupportActionBar().setTitle("Editar");
            try {
                Cursor cursor = banco.rawQuery(String.format("SELECT atv FROM atividades WHERE id = %d", ID), null);
                cursor.moveToFirst();
                atividadeInput.getEditText().setText(cursor.getString(cursor.getColumnIndex("atv")));
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERRO LOKO", Toast.LENGTH_SHORT ).show();
            }
        }
        toolbar.setTitleTextColor(getResources().getColor(R.color.white, null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(ID == 0){
            if(atividadeInput.getEditText().getText() != null || atividadeInput.getEditText().getText().toString() != ""){
                banco.execSQL("INSERT INTO atividades(atv) VALUES " + "('" + atividadeInput.getEditText().getText().toString() + "')" );
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "NADA PRA SALVAR", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(atividadeInput.getEditText().getText() != null || atividadeInput.getEditText().getText().toString() != ""){
                banco.execSQL(String.format("UPDATE atividades SET atv = %s WHERE id = %d",
                        "'" + atividadeInput.getEditText().getText().toString() + "'",
                        ID));
            }else{
                Toast.makeText(getApplicationContext(), "SALVAMENTO IMPEDIDO", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}