package com.uvigo.uvigotelecopsicg08.blackjackqlearning;
import java.io.Serializable;

public class EstadoAccion implements Serializable
{
    int PuntosJugador;
    int PuntosRival;

    double[] dValAction; //dos acciones el 1 es el hit y el 2 es el Stand


    public EstadoAccion(int puntosJugador, int puntosRival) {
        PuntosJugador = puntosJugador;
        PuntosRival = puntosRival;
        this.dValAction = new double[2];
    }



    public int getPuntosJugador() {
        return PuntosJugador;
    }

    public int getPuntosRival() {
        return PuntosRival;
    }

    public double dGetQAction (int i) {
        return dValAction[i];
    }
}