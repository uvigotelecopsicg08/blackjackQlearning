package com.uvigo.uvigotelecopsicg08.blackjackqlearning;
import java.io.Serializable;

public class EstadoAccion implements Serializable
{
    int PuntosJugador;
    int PuntosRival;
    int CartasRival;
    boolean ASJugador;
    boolean ASRival;


    double[] dValAction; //dos acciones el 1 es el hit y el 2 es el Stand


    public EstadoAccion(int puntosJugador, int puntosRival, int cartasRival, boolean AsJugador, boolean AsRival) {
        PuntosJugador = puntosJugador;
        PuntosRival = puntosRival;
        CartasRival =cartasRival;
        ASJugador =AsJugador;
        ASRival =AsRival;

        this.dValAction = new double[2];
    }



    public int getPuntosJugador() {
        return PuntosJugador;
    }

    public int getPuntosRival() {
        return PuntosRival;
    }

    public int getCartasRival() {
        return CartasRival;
    }

    public boolean getASJugador() {
        return ASJugador;
    }

    public boolean getASRival() {
        return ASRival;
    }

    public double dGetQAction (int i) {
        return dValAction[i];
    }
}