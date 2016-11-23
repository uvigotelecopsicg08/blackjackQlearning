package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juani on 20/11/2016.
 */

public class Carta   {

    private String palo; //diamantes, corazones, picas, treboles
    private String valor; //1, 2, 3... 10, jack, reina, rey
    private int cara = -1; //ruta de la imagen
    private boolean visible; //true=visible; false=no visible

    public Carta(String palo, String valor, int cara, boolean visible) {
        this.palo = palo;
        this.valor = valor;
        this.cara = cara;
        this.visible = visible;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getCara() {
        return cara;
    }

    public void setCara(int cara) {
        this.cara = cara;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return palo+"-"+valor+"-"+cara+"-"+visible+":";
    }
}
