package com.frederico.listas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.frederico.listas.R;
import com.frederico.listas.adapters.CitiesAdapter;
import com.frederico.listas.models.CitiesHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView CityRecycler;
    CitiesAdapter CityAdapter;

    private List<CitiesHandler> CityList = new ArrayList<>(Arrays.asList(
            new CitiesHandler("東京都", "日本", (n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("New York", "USA",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Petrópolis", "Brasil",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Berlin", "Deutschland",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Lisboa", "Portugal",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Paris", "France",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Toronto", "Canada",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Madrid", "España",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Ciudad de Mexico", "Mexico",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();}),
            new CitiesHandler("Butterworth", "South Africa",(n, c) ->{Toast.makeText(getApplicationContext(), n, Toast.LENGTH_SHORT).show();})
    ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CityRecycler = findViewById(R.id.cityList);

        CityAdapter = new CitiesAdapter(CityList);

        CityRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager managero = new LinearLayoutManager(getApplicationContext());
        CityRecycler.setLayoutManager(managero);
        CityRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        CityRecycler.setAdapter(CityAdapter);
    }
}