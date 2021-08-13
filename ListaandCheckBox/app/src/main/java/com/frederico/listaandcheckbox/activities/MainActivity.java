package com.frederico.listaandcheckbox.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.frederico.listaandcheckbox.R;
import com.frederico.listaandcheckbox.adapters.GamesAdapter;
import com.frederico.listaandcheckbox.models.GamesHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView GameRecycler;
    GamesAdapter GameAdapter;
    AppCompatButton ShowFavs;
    ArrayList<String> Favs = new ArrayList<>();
    List<GamesHandler> GameList = new ArrayList<>(Arrays.asList(
            new GamesHandler("Mortal Kombat", "Fighting", (this::editFav) ),
            new GamesHandler("GTA", "Open World | Action", (this::editFav) ),
            new GamesHandler("Outer Worlds", "Indie | SandBox | Adventure", (this::editFav) ),
            new GamesHandler("Aer", "Indie | Puzzle | Adventure", (this::editFav) ),
            new GamesHandler("A lenda do Heroi", "Indie Plataform", (this::editFav) ),
            new GamesHandler("League of Legends", "MOBA", (this::editFav) ),
            new GamesHandler("The Sims", "Simulation", (this::editFav) ),
            new GamesHandler("Control", "FPS | Adventure | Action", (this::editFav) ),
            new GamesHandler("Resident Evil 7", "Horror", (this::editFav) )
    ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameRecycler = findViewById(R.id.Game_Recycler);

        GameAdapter = new GamesAdapter(GameList);

        RecyclerView.LayoutManager managero = new LinearLayoutManager(getApplicationContext());
        GameRecycler.setLayoutManager(managero);
        GameRecycler.setHasFixedSize(true);
        GameRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        GameRecycler.setAdapter(GameAdapter);

        ShowFavs = findViewById(R.id.Show_Fav);
        ShowFavs.setOnClickListener((v -> {
            AlertDialog.Builder dialog =  new AlertDialog.Builder(this);
            dialog.setTitle("Favorited games");
            StringBuilder Message = new StringBuilder();
            for(String i : Favs){
                Message.append(i).append("\n");
            }
            dialog.setMessage(Message.toString());
            dialog.show();
        }));

    }
    public void editFav(String N, String G, int S){

        if(S == 0){
            Favs.remove(N);
        }else{
            Favs.add(N);
        }
    }
}