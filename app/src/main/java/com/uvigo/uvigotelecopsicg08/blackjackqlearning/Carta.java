package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

/**
 * Created by Juani on 20/11/2016.
 */

public class Carta {

    private String palo; //diamantes, corazones, picas, treboles
    private String valor; //1, 2, 3... 10, jack, reina, rey
    private String cara; //ruta de la imagen
    private String dorso; //ruta de la imagen
    private boolean visible; //true=visible; false=no visible

    public Carta(String palo, String valor, String cara, String dorso, boolean visible) {
        this.palo = palo;
        this.valor = valor;
        this.cara = cara;
        this.dorso = dorso;
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
    public String getCara() {
        return cara;
    }
    public void setCara(String cara) {
        this.cara = cara;
    }
    public String getDorso() {
        return dorso;
    }
    public void setDorso(String dorso) {
        this.dorso = dorso;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
