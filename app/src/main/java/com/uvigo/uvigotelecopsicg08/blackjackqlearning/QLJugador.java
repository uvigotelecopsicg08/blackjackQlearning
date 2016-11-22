package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.util.ArrayList;

/**
 * Created by Gabriel on 22/11/2016.
 */

public class QLJugador extends Jugador {
    int matrizQL[][][][];
    private ArrayList<String> jugadas= new ArrayList<String>();
    int hit=-1;
    public QLJugador(String nombre) {
        super(nombre);
        matrizQL=new int[20][20][6][2];
        // TODO Auto-generated constructor stub
		/*Maximo numero de veces que se puede llegar a hitear 6 que seria el equivalente a que cada uno tenga
		la mitad de las cartas más bajas */
		/*el 0 es hitear y el 1 es plantarse*/
    }

    /*public void HacerJugada(Mazo mazo){
        if (matrizQL[getPuntos1()-1][getPuntos2()-1][hit][0]<matrizQL[getPuntos1()-1][getPuntos2()-1][hit][1])
            pedirCarta(mazo);
        else plantarse();
    }*/
}
