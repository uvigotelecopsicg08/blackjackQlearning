package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.util.ArrayList;

/**
 * Created by Juani on 20/11/2016.
 */

public class Jugador {
    private String nombre = "";
    private int puntos1 = 0;
    private int puntos2 = 0;
    private ArrayList<Carta> mano = new ArrayList<Carta>();
    private boolean as = false; //boolean que indica si el jugador tiene as
    private boolean asRival = false; //boolean que indica si el jugador tiene as
    private boolean plantado = false; //boolean que indica si el jugador está plantado
    private int ManoRival;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    public Jugador(){

    }

    public int getManoRival() {
        return ManoRival;
    }

    public void setManoRival(int manoRival) {
        ManoRival = manoRival;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public int getPuntos1() {
        return puntos1;
    }

    public int getPuntos2() {
        return puntos2;
    }

    public void setPuntos1(int puntos1) {
        this.puntos1 = puntos1;
    }

    public void setPuntos2(int puntos2) {
        this.puntos2 = puntos2;
    }

    public boolean isPlantado() {
        return plantado;
    }

    public void setPlantado(boolean plantado) {
        this.plantado = plantado;
    }

    public boolean hasAs() {
        return as;
    }

    public void setAs(boolean as) {
        this.as = as;
    }

    public void updateAs() {
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getValor().equals("1")) as = true;
        }
    }

    public void vaciarMano() {
        mano = new ArrayList<Carta>();
        as = false;
        puntos1 = 0;
        puntos2 = 0;
        plantado = false;
    }

    public void añadirCarta(Carta carta, boolean visible) {
        carta.setVisible(visible);
        mano.add(carta);
    }
    public boolean hasAsRival() {
        return asRival;
    }

    public void setAsRival(boolean as) {
        asRival = as;
    }

    public void pedirCarta(Mazo mazo) {
        mazo.darCarta(this);
    }

    public void plantarse() {
        plantado = true;
    }

    public boolean HacerJugada(Mazo mazo) {
    /*Definimos la jugada del jugador, para ello antes tendremos que actualizar en la partida tanto
		sus puntos como los del contrario*/
	/*En el jugador definiremos al jugador estandard que cada vez que tenga el 16 se planta*/
        if (puntos1 < 16) {
            pedirCarta(mazo);
            return true;
        } else {
            plantarse();
            return false;
        }
    }
}
