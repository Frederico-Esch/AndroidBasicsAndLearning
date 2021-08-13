package com.frederico.drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

public class EditFragment extends Fragment {
    private TextInputLayout editName;
    private TextInputLayout editEmail;
    private TextInputLayout editAge;
    private TextInputLayout editCel;
    private EditfragEditEvent clickEvent;
    public String Name = "", Age = "", Email = "", Cel = "";

    public EditFragment(EditfragEditEvent event){
        clickEvent = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag =  inflater.inflate(R.layout.fragment_edit, container, false);
        editName = frag.findViewById(R.id.edit_frag_name);
        editEmail = frag.findViewById(R.id.edit_frag_email);
        editAge = frag.findViewById(R.id.edit_frag_age);
        editCel = frag.findViewById(R.id.edit_frag_cel);

        editName.getEditText().addTextChangedListener(new TextWatcher() {
            int q;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                q = s.toString().replaceAll("\\s{2,}", " ").trim().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().replaceAll("\\s{2,}", " ").trim().length() > q){
                    Name = s.toString();
                    clickEvent.onEdit(Name.trim(), Email.trim(), Age.trim(), Cel.trim());
                }
            }
        });

        editEmail.getEditText().addTextChangedListener(new TextWatcher() {
            int q;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                q = s.toString().replaceAll("\\s{2,}", " ").trim().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().replaceAll("\\s{2,}", " ").trim().length() > q){
                    Email = s.toString();
                    clickEvent.onEdit(Name.trim(), Email.trim(), Age.trim(), Cel.trim());
                }
            }
        });

        editAge.getEditText().addTextChangedListener(new TextWatcher() {
            int q;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                q = s.toString().replaceAll("\\s{2,}", " ").trim().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().replaceAll("\\s{2,}", " ").trim().length() > q){
                    Age = s.toString();
                    clickEvent.onEdit(Name.trim(), Email.trim(), Age.trim(), Cel.trim());
                }
            }
        });

        editCel.getEditText().addTextChangedListener(new TextWatcher() {
            int q;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                q = s.toString().replaceAll("\\s{2,}", " ").trim().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().replaceAll("\\s{2,}", " ").trim().length() > q){
                    Cel = s.toString();
                    clickEvent.onEdit(Name.trim(), Email.trim(), Age.trim(), Cel.trim());
                }
            }
        });



        return frag;
    }

    public interface EditfragEditEvent{
        public void onEdit(String name, String email, String age, String cel);
    }
}