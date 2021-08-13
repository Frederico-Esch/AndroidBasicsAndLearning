package com.frederico.drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    private String Name = "", Age = "", Email = "", Cel = "";
    public ProfileFragment(String name,String email,String age,String cel) {
        Name = name;
        Age = age;
        Email = email;
        Cel = cel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag =  inflater.inflate(R.layout.fragment_profile, container, false);
        TextView textViewHolder = frag.findViewById(R.id.profile_frag_name);
        textViewHolder.setText(Name);
        textViewHolder = frag.findViewById(R.id.profile_frag_age);
        textViewHolder.setText(Age);
        textViewHolder = frag.findViewById(R.id.profile_frag_email);
        textViewHolder.setText(Email);
        textViewHolder = frag.findViewById(R.id.profile_frag_cel);
        textViewHolder.setText(Cel);
        return frag;
    }
}