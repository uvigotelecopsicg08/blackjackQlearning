package com.uvigo.uvigotelecopsicg08.blackjackqlearning;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Gabriel on 22/11/2016.
 */

public class JugadorHumano extends Jugador {
    public JugadorHumano(String nombre) {
        super(nombre);
        // TODO Auto-generated constructor stub
    }


    /*public void HacerJugada(Mazo mazo){
        Scanner sc = new Scanner(System.in);
        System.out.println("Puntos JugadorHumano:"+getPuntos1());
        System.out.println("Puntos JugadorMaquina:"+getPuntos2());
        System.out.println("El tamaño de la mano del rival es:"+ getManoRival()+"  Ha hecho "+(getManoRival()-2)+" hits");
        System.out.println("Elige la jugada Hit(1) o Stand (2):");
        int jugada = sc.nextInt();
        if (jugada==1)
            pedirCarta(mazo);
        else plantarse();
    }*/
}
