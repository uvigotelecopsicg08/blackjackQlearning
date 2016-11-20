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
    public void inicializar(){
        cartas = null;
        //diamantes
        cartas.add(new Carta("diamantes", "1", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "2", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "3", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "4", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "5", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "6", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "7", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "8", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "9", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "10", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "jack", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "reina", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("diamantes", "rey", "imagen_cara", "imagen_dorso", false));
        //corazones
        cartas.add(new Carta("corazones", "1", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "2", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "3", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "4", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "5", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "6", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "7", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "8", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "9", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "10", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "jack", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "reina", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("corazones", "rey", "imagen_cara", "imagen_dorso", false));
        //picas
        cartas.add(new Carta("picas", "1", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "2", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "3", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "4", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "5", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "6", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "7", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "8", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "9", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "10", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "jack", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "reina", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("picas", "rey", "imagen_cara", "imagen_dorso", false));
        //treboles
        cartas.add(new Carta("treboles", "1", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "2", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "3", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "4", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "5", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "6", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "7", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "8", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "9", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "10", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "jack", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "reina", "imagen_cara", "imagen_dorso", false));
        cartas.add(new Carta("treboles", "rey", "imagen_cara", "imagen_dorso", false));
    }
    public void barajar(){
        for(int i = cartas.size()-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(rand));
            cartas.set(rand, temp);
        }
    }
    public void repartirCartas(Jugador jug1, Jugador jug2){
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
    }
    public void darCarta(Jugador jug){
        Carta carta = cartas.get(0);
        jug.añadirCarta(carta, false);
        cartas.remove(0);
    }
    public int cartasRestantes(){
        return cartas.size();
    }
}
