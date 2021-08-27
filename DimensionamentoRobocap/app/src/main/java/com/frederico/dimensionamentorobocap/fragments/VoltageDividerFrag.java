package com.frederico.dimensionamentorobocap.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frederico.dimensionamentorobocap.R;
import com.google.android.material.textfield.TextInputLayout;

public class VoltageDividerFrag extends Fragment {
    RadioGroup radioGroup;
    TextInputLayout Z1, Z2, Vin, Vout;
    AppCompatButton calculateBut;
    int valor = -1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag =  inflater.inflate(R.layout.fragment_voltage_divider, container, false);
        radioGroup = frag.findViewById(R.id.selector);
        Z1 = frag.findViewById(R.id.Z1);
        Z2 = frag.findViewById(R.id.Z2);
        Vout = frag.findViewById(R.id.Vout);
        Vin = frag.findViewById(R.id.Vin);
        calculateBut = frag.findViewById(R.id.calculateButton);

        Z1.getEditText().setEnabled(false);
        Z2.getEditText().setEnabled(false);
        Vout.getEditText().setEnabled(false);
        Vin.getEditText().setEnabled(false);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.selectorZ1:{
                    valor = 0;
                    break;
                }
                case R.id.selectorZ2:{
                    valor = 1;
                    break;
                }
                case R.id.selectorVin:{
                    valor = 2;
                    break;
                }
                case R.id.selectorVout:{
                    valor = 3;
                    break;
                }
            }
            free(valor);
        });
        calculateBut.setOnClickListener(this::calculate);
        return frag;
    }
    public void calculate(View v){
        switch(valor){
            case -1:{
                break;
            }
            case 0:{//Calculando Z1
                try {
                    float valueZ2 = Integer.parseInt(Z2.getEditText().getText().toString());
                    float valueVout = Integer.parseInt(Vout.getEditText().getText().toString());
                    float valueVin =  Integer.parseInt(Vin.getEditText().getText().toString());
                    if (valueZ2 != 0 && valueVout != 0 & valueVin != 0){
                        float valueZ1 = valueZ2 * (valueVin-valueVout)/valueVout;
                        Z1.getEditText().setText(String.valueOf(valueZ1));
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case 1: {//calcular Z2
                try {
                    float valueZ1 = Integer.parseInt(Z1.getEditText().getText().toString());
                    float valueVout = Integer.parseInt(Vout.getEditText().getText().toString());
                    float valueVin = Integer.parseInt(Vin.getEditText().getText().toString());
                    if (valueZ1 != 0 && valueVout != 0 & valueVin != 0) {
                        float valueZ2 = valueVout * valueZ1 / (valueVin - valueVout);
                        Z2.getEditText().setText(String.valueOf(valueZ2));
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
                break;
            } case 2: {//calcular Vin
                try {
                    float valueZ1 = Integer.parseInt(Z1.getEditText().getText().toString());
                    float valueVout = Integer.parseInt(Vout.getEditText().getText().toString());
                    float valueZ2 = Integer.parseInt(Z2.getEditText().getText().toString());
                    if(valueZ2 != 0 && valueZ1 != 0 && valueVout != 0){
                        float valueVin = valueVout * (valueZ1 + valueZ2)/valueZ2;
                        Vin.getEditText().setText(String.valueOf(valueVin));
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
                break;
            } case 3:{//calcular Vout
                try {
                    float valueZ1 = Integer.parseInt(Z1.getEditText().getText().toString());
                    float valueVin = Integer.parseInt(Vin.getEditText().getText().toString());
                    float valueZ2 = Integer.parseInt(Z2.getEditText().getText().toString());
                    if(valueVin != 0 && valueZ1 != 0 && valueZ2 != 0){
                        float valueVout = valueVin * valueZ2/(valueZ1 + valueZ2);
                        Vout.getEditText().setText(String.valueOf(valueVout));
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public void free(int valor){
        Z1.getEditText().setEnabled(true);
        Z2.getEditText().setEnabled(true);
        Vout.getEditText().setEnabled(true);
        Vin.getEditText().setEnabled(true);
        switch (valor){
            case 0:{
                Z1.getEditText().setEnabled(false);
                break;
            }
            case 1:{
                Z2.getEditText().setEnabled(false);
                break;
            }
            case 2:{
                Vin.getEditText().setEnabled(false);
                break;
            }
            case 3:{
                Vout.getEditText().setEnabled(false);
                break;
            }
            default:{
                Toast.makeText(getContext(), R.string.navigation_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}