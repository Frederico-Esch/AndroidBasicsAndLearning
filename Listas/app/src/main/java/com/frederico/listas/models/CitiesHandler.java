package com.frederico.listas.models;


import java.util.function.Function;

public class CitiesHandler {
    private String Name;
    private String Country;
    private funcBot func;
    public CitiesHandler(String name, String country, funcBot func){
        Name = name;
        Country = country;
        this.func = func;
    }
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public funcBot getFunc() {
        return func;
    }

    public interface funcBot {
        void butClic(String N, String C);
    }
}
