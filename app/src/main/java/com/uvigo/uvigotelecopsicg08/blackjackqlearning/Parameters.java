package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.io.Serializable;

/**
 * Created by Juani on 29/11/2016.
 */

public class Parameters implements Serializable {
    Parameters(){
        color="Verde";
        numRondas=10;
        music=false;
        facil=false;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumRondas() {
        return numRondas;
    }

    public void setNumRondas(int numRondas) {
        this.numRondas = numRondas;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isFacil() {
        return facil;
    }

    public void setFacil(boolean facil) {
        this.facil = facil;
    }

    String color;
    int numRondas;
    boolean music,facil;
}
