package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.util.ArrayList;

/**
 * Created by Juani on 20/11/2016.
 */

public class Mazo {
    private ArrayList<Carta> cartas = new ArrayList<Carta>();

    public Mazo() {
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void inicializar() {
        //cartas.removeAll();
        //diamantes
        cartas.add(new Carta("diamantes", "1", R.drawable.d1, false));
        cartas.add(new Carta("diamantes", "2", R.drawable.d2, false));
        cartas.add(new Carta("diamantes", "3", R.drawable.d3, false));
        cartas.add(new Carta("diamantes", "4", R.drawable.d4, false));
        cartas.add(new Carta("diamantes", "5", R.drawable.d5, false));
        cartas.add(new Carta("diamantes", "6", R.drawable.d6, false));
        cartas.add(new Carta("diamantes", "7", R.drawable.d7, false));
        cartas.add(new Carta("diamantes", "8", R.drawable.d8, false));
        cartas.add(new Carta("diamantes", "9", R.drawable.d9, false));
        cartas.add(new Carta("diamantes", "10", R.drawable.d10, false));
        cartas.add(new Carta("diamantes", "jack", R.drawable.d11, false));
        cartas.add(new Carta("diamantes", "reina", R.drawable.d12, false));
        cartas.add(new Carta("diamantes", "rey", R.drawable.d13, false));
        //corazones
        cartas.add(new Carta("corazones", "1", R.drawable.h1, false));
        cartas.add(new Carta("corazones", "2", R.drawable.h2, false));
        cartas.add(new Carta("corazones", "3", R.drawable.h3, false));
        cartas.add(new Carta("corazones", "4", R.drawable.h4, false));
        cartas.add(new Carta("corazones", "5", R.drawable.h5, false));
        cartas.add(new Carta("corazones", "6", R.drawable.h6, false));
        cartas.add(new Carta("corazones", "7", R.drawable.h7, false));
        cartas.add(new Carta("corazones", "8", R.drawable.h8, false));
        cartas.add(new Carta("corazones", "9", R.drawable.h9, false));
        cartas.add(new Carta("corazones", "10", R.drawable.h10, false));
        cartas.add(new Carta("corazones", "jack", R.drawable.h11, false));
        cartas.add(new Carta("corazones", "reina", R.drawable.h12, false));
        cartas.add(new Carta("corazones", "rey", R.drawable.h13, false));
        //picas
        cartas.add(new Carta("picas", "1", R.drawable.s1, false));
        cartas.add(new Carta("picas", "2", R.drawable.s2, false));
        cartas.add(new Carta("picas", "3", R.drawable.s3, false));
        cartas.add(new Carta("picas", "4", R.drawable.s4, false));
        cartas.add(new Carta("picas", "5", R.drawable.s5, false));
        cartas.add(new Carta("picas", "6", R.drawable.s6, false));
        cartas.add(new Carta("picas", "7", R.drawable.s7, false));
        cartas.add(new Carta("picas", "8", R.drawable.s8, false));
        cartas.add(new Carta("picas", "9", R.drawable.s9, false));
        cartas.add(new Carta("picas", "10", R.drawable.s10, false));
        cartas.add(new Carta("picas", "jack", R.drawable.s11, false));
        cartas.add(new Carta("picas", "reina", R.drawable.s12, false));
        cartas.add(new Carta("picas", "rey", R.drawable.s13, false));
        //treboles
        cartas.add(new Carta("treboles", "1", R.drawable.c1, false));
        cartas.add(new Carta("treboles", "2", R.drawable.c2, false));
        cartas.add(new Carta("treboles", "3", R.drawable.c3, false));
        cartas.add(new Carta("treboles", "4", R.drawable.c4, false));
        cartas.add(new Carta("treboles", "5", R.drawable.c5, false));
        cartas.add(new Carta("treboles", "6", R.drawable.c6, false));
        cartas.add(new Carta("treboles", "7", R.drawable.c7, false));
        cartas.add(new Carta("treboles", "8", R.drawable.c8, false));
        cartas.add(new Carta("treboles", "9", R.drawable.c9, false));
        cartas.add(new Carta("treboles", "10", R.drawable.c10, false));
        cartas.add(new Carta("treboles", "jack", R.drawable.c11, false));
        cartas.add(new Carta("treboles", "reina", R.drawable.c12, false));
        cartas.add(new Carta("treboles", "rey", R.drawable.c13, false));
    }

    public void barajar() {
        for (int i = cartas.size() - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(rand));
            cartas.set(rand, temp);
        }
    }

    public void repartirCartas(Jugador jug1, Jugador jug2) {
        jug1.vaciarMano();
        jug2.vaciarMano();
        Carta carta = cartas.get(0);
        jug1.añadirCarta(carta, true);
        cartas.remove(0);
        carta = cartas.get(0);
        jug2.añadirCarta(carta, true);
        cartas.remove(0);
        carta = cartas.get(0);
        jug1.añadirCarta(carta, true);
        cartas.remove(0);
        carta = cartas.get(0);
        jug2.añadirCarta(carta, true);
        cartas.remove(0);
        jug1.updateAs();
        jug2.updateAs();
    }

    public void darCarta(Jugador jug) {
        Carta carta = cartas.get(0);
        jug.añadirCarta(carta, false);
        jug.updateAs();
        cartas.remove(0);
    }

    public int cartasRestantes() {
        return cartas.size();
    }
}
