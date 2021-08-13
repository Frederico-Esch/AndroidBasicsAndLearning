package com.frederico.jokenpoxavida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView computerImage, scisors, paper, rock;
    TextView gameStatus, computerChoice;
    ViewGroup.LayoutParams buttonLayout;
    Random random = new Random();
    int played;
    boolean pastState = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scisors = findViewById(R.id.scisorsButton);
        paper = findViewById(R.id.paperButton);
        rock = findViewById(R.id.rockButton);
        computerImage = findViewById(R.id.computerImage);
        gameStatus = findViewById(R.id.gameStatus);
        computerChoice = findViewById(R.id.computerChoice);

        scisors.setOnTouchListener(this::Clicou);
        rock.setOnTouchListener(this::Clicou);
        paper.setOnTouchListener(this::Clicou);
    }


    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    public boolean Clicou(View b, MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                buttonLayout = b.getLayoutParams();
                buttonLayout.width += 10;
                b.setLayoutParams(buttonLayout);
                pastState = true;
                break;
            case MotionEvent.ACTION_UP:
                buttonLayout = b.getLayoutParams();
                buttonLayout.width = getResources().getDimensionPixelSize(R.dimen.buttonSize);
                b.setLayoutParams(buttonLayout);
                pastState = false;
                break;
        }
        switch (b.getId()){
            case R.id.rockButton:
                played = 0;
                break;
            case R.id.paperButton:
                played = 1;
                break;
            case R.id.scisorsButton:
                played = 2;
                break;
        }
        if(!pastState){
            Play();
        }
        Chose();
        return false;
    }
    public void Play(){
        int comp = random.nextInt(3);
        switch(comp){
            case 0:
                computerImage.setImageResource(R.drawable.rock);
                break;
            case 1:
                computerImage.setImageResource(R.drawable.paper);
                break;
            case 2:
                computerImage.setImageResource(R.drawable.scisors);
                break;
        }
        switch (played - comp){
            case 1:
                //player ganhou
                gameStatus.setText(getString(R.string.Win));
                break;
            case 2:
                gameStatus.setText(getString(R.string.Lost));
                break;
            case -1:
                gameStatus.setText(getString(R.string.Lost));
                break;
            case -2:
                gameStatus.setText(getString(R.string.Win));
                break;
            case 0:
                gameStatus.setText(getString(R.string.Tie));
                break;
        }
    }
    private void Chose(){
        computerChoice.setText(getString(R.string.computerChose));
    }
}