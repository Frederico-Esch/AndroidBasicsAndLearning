package com.frederico.listas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.frederico.listas.R;
import com.frederico.listas.adapters.atvAdapter;
import com.frederico.listas.models.atvModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<atvModel> atvList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.lista_atvs);

        try{
            SQLiteDatabase banco = openOrCreateDatabase("lista", MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS atividades(id INTEGER PRIMARY KEY AUTOINCREMENT, atv VARCHAR)");
            Cursor cursor = banco.rawQuery("SELECT * FROM atividades", null);
            cursor.moveToFirst();

            int size = cursor.getCount();
            int atvINDEX = cursor.getColumnIndex("atv");
            int idINDEX = cursor.getColumnIndex("id");
            for(int i = 0; i < size; i++){
                atvList.add(new atvModel(cursor.getString(atvINDEX), cursor.getInt(idINDEX), new ExcludeAndEdit()));
                cursor.moveToNext();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "ERROS LOKOS", Toast.LENGTH_SHORT).show();
        }

        atvAdapter adapter = new atvAdapter(atvList);

        LinearLayoutManager managero = new LinearLayoutManager(getApplicationContext());
        managero.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(managero);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        botao = findViewById(R.id.botao);
        botao.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditCreate.class);
            intent.putExtra("ID", 0);
            startActivity(intent);
        });
    }

    class ExcludeAndEdit implements atvModel.Press{
        @Override
        public void Short(int id) {
            Intent intent = new Intent(getApplicationContext(), EditCreate.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }

        @Override
        public void Long(int id) {
            try {
                SQLiteDatabase banco = openOrCreateDatabase("lista", MODE_PRIVATE, null);
                banco.execSQL(String.format("DELETE FROM atividades WHERE id = %d", id));
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERRO CABULOSO", Toast.LENGTH_SHORT).show();
            }
            recreate();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
    /*
    public void addEntry(View v){
        try {
            SQLiteDatabase banco = openOrCreateDatabase("lista", MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS atividades(id INTEGER PRIMARY KEY AUTOINCREMENT, atv VARCHAR)");
            banco.execSQL("INSERT INTO atividades(atv) VALUES ('BBBBBBBB')");
        }catch (Exception e){
            System.out.println("ERRO");
        }
    }

    public void printEntry(View v){
        try {
            SQLiteDatabase banco = openOrCreateDatabase("lista", MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS atividades(id INTEGER PRIMARY KEY AUTOINCREMENT, atv VARCHAR)");

            Cursor cursor = banco.rawQuery("SELECT * FROM atividades", null );
            cursor.moveToFirst();
            int size = cursor.getCount();

            int idIndex = cursor.getColumnIndex("id");
            int atvIndex = cursor.getColumnIndex("atv");
            for(int i = 0; i < size; i++){
                System.out.println(cursor.getString(idIndex));
                System.out.println(cursor.getString(atvIndex));
                cursor.moveToNext();
            }

        }catch (Exception e){
            System.out.println("ERRO");
        }
    }*/
}