package com.frederico.firebasefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class CriarEditar extends AppCompatActivity {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    Toolbar toolbar;
    String ID;
    TextInputLayout titleInput;
    TextInputLayout contentInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_editar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleInput = findViewById(R.id.title_input);
        contentInput = findViewById(R.id.content_input);

        ID = getIntent().getStringExtra("id");
        if(ID == null){
            getSupportActionBar().setTitle("Criar");
            toolbar.setTitleTextColor(getColor(R.color.white));
        }else{
            getSupportActionBar().setTitle("Editar");
            toolbar.setTitleTextColor(getColor(R.color.white));
            titleInput.setEnabled(false);
            contentInput.setEnabled(false);
            populateInputs();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
    }

    void populateInputs(){
            reference.child("tarefas").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snap : snapshot.getChildren()){
                        if(snap.getKey().equals(ID)){
                            titleInput.getEditText().setText(snap.child("titulo").getValue().toString());
                            contentInput.getEditText().setText(snap.child("conteudo").getValue().toString());
                            break;
                        }
                    }
                    titleInput.setEnabled(true);
                    contentInput.setEnabled(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_button:{
                String title = titleInput.getEditText().getText().toString();
                String content = contentInput.getEditText().getText().toString();
                if(title.matches("[\\s]") || content.matches("[\\s]") || content.length() <= 0 || title.length() <=0){
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_SHORT).show();
                    return super.onOptionsItemSelected(item);
                }

                if(ID == null) ID = reference.child("tarefas").push().getKey();

                reference.child("tarefas").child(ID).child("titulo").setValue(title);
                reference.child("tarefas").child(ID).child("conteudo").setValue(content);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}