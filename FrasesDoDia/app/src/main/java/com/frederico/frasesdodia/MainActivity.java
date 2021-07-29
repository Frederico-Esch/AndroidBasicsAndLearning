package com.frederico.frasesdodia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView Frase;
    Button NewFrase;
    String[] FrasesRepo = {
            "Todo mundo merece ser feliz, mas quase ninguém vai conseguir",
            "Onde houver a concorrência serei a desistência",
            "Algumas escolhas mudam todo o rumo da sua vida, escolha desistir",
            "A dor que você sente agora vai durar até a sua morte",
            "Mil cairão a tua esquerda, dois mil cairão a tua direita, mas nada cairá em tua conta corrente",
            "Lembra quando diziam que você ia fracassar. Pois, estavam corretos",
            "Não tente esconder suas fraquezas. Você é insuficiente e todos ao seu redor podem ver"
    };
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Frase = findViewById(R.id.textView);
        NewFrase = findViewById(R.id.butNewPhrase);
        NewFrase.setOnClickListener(v -> {
            int valor = random.nextInt(FrasesRepo.length);
            Frase.setText(FrasesRepo[valor]);
        });
    }
}