package com.frederico.dimensionamentorobocap.models;

import java.io.Serializable;

public class CircuitHandler implements Serializable {
    String Name;
    int Src_Image;
    int Max_Height;
    EnterCircuit enterCircuit;

    public CircuitHandler(String name, int src_Image, int max_Height){
        Name = name;
        Src_Image = src_Image;
        Max_Height = max_Height;
    }

    public String getName() {
        return Name;
    }

    public int getMax_Height() {
        return Max_Height;
    }

    public int getSrc_Image() {
        return Src_Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setMax_Height(int max_Height) {
        Max_Height = max_Height;
    }

    public void setSrc_Image(int src_Image) {
        Src_Image = src_Image;
    }

    public EnterCircuit getEnterCircuit() {
        return enterCircuit;
    }

    public void setEnterCircuit(EnterCircuit enterCircuit) {
        this.enterCircuit = enterCircuit;
    }

    public interface EnterCircuit{
        void Click(String N, int S_I);
    }
}
