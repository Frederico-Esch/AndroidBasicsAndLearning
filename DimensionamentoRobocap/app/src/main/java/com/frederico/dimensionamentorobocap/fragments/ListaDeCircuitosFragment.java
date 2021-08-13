package com.frederico.dimensionamentorobocap.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frederico.dimensionamentorobocap.R;
import com.frederico.dimensionamentorobocap.adapters.CircuitAdapter;
import com.frederico.dimensionamentorobocap.models.CircuitHandler;

import java.util.ArrayList;

public class ListaDeCircuitosFragment extends Fragment {

    RecyclerView circuitRecycler;
    CircuitAdapter adapter;
    ArrayList<CircuitHandler> circuitArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag =  inflater.inflate(R.layout.fragment_lista_de_circuitos, container, false);

        circuitArrayList = (ArrayList<CircuitHandler>) getArguments().getSerializable("array");

        circuitRecycler = frag.findViewById(R.id.circuit_recycler_main_page);
        adapter = new CircuitAdapter(circuitArrayList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(RecyclerView.VERTICAL);

        circuitRecycler.setHasFixedSize(true);
        circuitRecycler.setLayoutManager(manager);
        circuitRecycler.setAdapter(adapter);

        return frag;
    }


}