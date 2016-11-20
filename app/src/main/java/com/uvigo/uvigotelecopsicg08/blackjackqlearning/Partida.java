package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

/**
 * Created by Juani on 20/11/2016.
 */

public class Partida {
    //datos de la partida
    private static boolean jugadorListo, agenteListo; //boolean que indica si el jugador/agente ha tomado una decisión
    private static Jugador jugador, agente;
    private static Mazo mazo;
    private static int numRondas, rondasJugadas=0; //numero de rondas por partida
    private static int puntosJugador=0, puntosAgente=0; //numero de rondas ganadas

    public static void main(String[] args) {
        //inicializamos partida
        jugador = new Jugador("jugador");
        agente = new Jugador("agente");

        while(numRondas!=rondasJugadas){
            mazo.inicializar();
            mazo.barajar();
            mazo.repartirCartas(jugador, agente);


            rondasJugadas++;
        }

        System.out.println("Partida finalizada.");
        if(puntosJugador==puntosAgente) System.out.println("Los jugadores han empatado.");
        else if(puntosJugador>puntosAgente) System.out.println("El jugador ha ganado.");
        else System.out.println("El agente ha ganado");
    }
}
