package com.frederico.dimensionamentorobocap.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.frederico.dimensionamentorobocap.R;
import com.frederico.dimensionamentorobocap.adapters.CircuitAdapter;
import com.frederico.dimensionamentorobocap.fragments.ListaDeCircuitosFragment;
import com.frederico.dimensionamentorobocap.fragments.VoltageDividerFrag;
import com.frederico.dimensionamentorobocap.models.CircuitHandler;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //RecyclerView circuitRecycler;
    //CircuitAdapter adapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ArrayList<CircuitHandler> circuitArrayList = new ArrayList<>();
    int last_circuit = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buildCircuitArrayList();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navView);

        drawerLayout = findViewById(R.id.drawer_layout);
        buildCircuitArrayList();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            changeFrag(true);
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_lista:{
                    changeFrag(true);
                    break;
                }
                case R.id.menu_circuito:{
                    changeFrag();
                    break;
                }
            }
            return true;
        });


    }


    public void buildCircuitArrayList(){
        CircuitHandler circuitHandler = new CircuitHandler(getString(R.string.voltage_divider), R.drawable.voltage_divider, 200);
        circuitHandler.setEnterCircuit((N, S_I)->{
            last_circuit = 0;
            changeFrag();
        });
        circuitArrayList.add(circuitHandler);
        //Place holders
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
        circuitArrayList.add(new CircuitHandler("PLACE HOLDER", R.drawable.voltage_divider, 200));
    }


    public void changeFrag(){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch (last_circuit){
            case -1:{
                Toast.makeText(getApplicationContext(), R.string.no_selection,Toast.LENGTH_SHORT).show();
            }
            case 0:{
                trans.replace(R.id.frag_holder, new VoltageDividerFrag()).commit();
                navigationView.setCheckedItem(R.id.menu_circuito);
                break;
            }
        }
    }

    public void changeFrag(boolean lista){
        if(lista) {
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putSerializable("array", (Serializable) circuitArrayList);
            ListaDeCircuitosFragment listaDeCircuitosFragment = new ListaDeCircuitosFragment();
            listaDeCircuitosFragment.setArguments(bundle);
            trans.replace(R.id.frag_holder, listaDeCircuitosFragment).commit();
            navigationView.setCheckedItem(R.id.menu_lista);
        }else{
            Toast.makeText(getApplicationContext(), R.string.no_selection,Toast.LENGTH_SHORT).show();
        }
    }


}