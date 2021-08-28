package com.frederico.dimensionamentorobocap.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.frederico.dimensionamentorobocap.R;

public class ListaDeComponentes extends AppCompatActivity {
    AppCompatButton listar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_componentes);
        listar = findViewById(R.id.listar_componentes);
        listar.setOnClickListener(this::click);
    }

    public void click(View v){
        try {
            SQLiteDatabase banco = openOrCreateDatabase("lista_de_componentes", MODE_PRIVATE, null);
            Cursor cursor = banco.rawQuery("SELECT circuito, tipo, valor FROM lista", null);
            int indexCirc = cursor.getColumnIndex("circuito");
            int indexTipo = cursor.getColumnIndex("tipo");
            int indexValor = cursor.getColumnIndex("valor");
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount();i++){
                System.out.println(String.format("Circuito - %s, Tipo - %s, Valor - %s",
                        cursor.getString(indexCirc),
                        cursor.getString(indexTipo),
                        cursor.getString(indexValor)));
                cursor.moveToNext();
            }
            System.out.println("FIM");
        }catch (Exception e){
            System.out.println(e.toString());
            Toast.makeText(getApplicationContext(), "Algo deu Errado", Toast.LENGTH_SHORT).show();
        }
    }
}