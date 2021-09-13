package com.frederico.listas.models;

import android.view.View;

public class atvModel {
    private String Text;
    private int Id;
    private Press press;
    public atvModel(String text, int id, Press pres){
        Text = text;
        Id = id;
        press = pres;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getId() {
        return Id;
    }

    public Press getPress() {
        return press;
    }

    public interface Press{
        public void Short(int id);
        public void Long(int id);
    }
}
