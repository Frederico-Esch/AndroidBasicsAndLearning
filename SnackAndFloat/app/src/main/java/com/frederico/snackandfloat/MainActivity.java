package com.frederico.snackandfloat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    MaterialToolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.secondary_text);
        textView.setText("AAAAAAAAAAAAAAAA");
        toolbar.setElevation(20);
        toolbar.setTitleCentered(true);
        fab.setOnClickListener(v ->Snackbar.make(fab, "OIE", Snackbar.LENGTH_INDEFINITE)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        return;
                    }
                }).show());
    }
}