package com.frederico.listaandcheckbox.models;

import android.widget.CheckBox;

public class GamesHandler {
    String Name;
    String Genre;
    favoring CheckButton;
    public GamesHandler(String name, String genre, favoring checkbutton){
        Genre = genre;
        Name = name;
        CheckButton = checkbutton;
    }

    public String getName() {
        return Name;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setName(String name) {
        Name = name;
    }

    public favoring getCheckButton() {
        return CheckButton;
    }

    public interface favoring{
        void checked(String N, String G, int S);
    }
}
