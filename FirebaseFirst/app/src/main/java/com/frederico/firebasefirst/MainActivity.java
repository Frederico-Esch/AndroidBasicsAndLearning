package com.frederico.firebasefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frederico.firebasefirst.adapters.TarefaAdapter;
import com.frederico.firebasefirst.models.TarefaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    Toolbar toolbar;
    ArrayList<TarefaModel> lista = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler);

        fillAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Log.i("QUERO MORRE", "A");
        Intent intent = new Intent(this, CriarEditar.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void fillAdapter(){
        reference.child("tarefas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id,title, content;
                for(DataSnapshot snap: snapshot.getChildren()){
                    id = snap.getKey();
                    title = snap.child("titulo").getValue().toString();
                    content = snap.child("conteudo").getValue().toString();
                    lista.add(new TarefaModel(id, title, content, new pressionar()));
                }
                TarefaAdapter adapter = new TarefaAdapter(lista);
                LinearLayoutManager managero = new LinearLayoutManager(getApplicationContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(managero);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class pressionar implements TarefaModel.Press{

        @Override
        public void Short(String id) {
            Intent intent = new Intent(getApplicationContext(), CriarEditar.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }

        @Override
        public void Long(String id) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Atenção");
            alert.setIcon(R.drawable.ic_baseline_warning_24);
            alert.setMessage("Você tem certeza que deseja deletar esta Tarefa?");
            boolean response = false;
            alert.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    deleteTarefa(id);
                }
            });
            alert.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.create().show();
        }
    }

    void deleteTarefa(String id){
        reference.child("tarefas").child(id).setValue(null);
        recreate();
    }
}