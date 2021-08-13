package com.frederico.dimensionamentorobocap.fragments;

import android.os.Bundle;

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

        Z1.getEditText().setEnabled(false);
        Z2.getEditText().setEnabled(false);
        Vout.getEditText().setEnabled(false);
        Vin.getEditText().setEnabled(false);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int valor = -1;
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
        return frag;
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