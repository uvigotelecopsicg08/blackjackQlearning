package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Gabriel on 22/11/2016.
 */

public class QLJugador extends Jugador {
    /*double alpha=0.6 ;  //Valores de la Thesis
double gamma=0.75;
double epsilon=0.5;*/
    double alpha=0.9 ;  //Valores del Codigo
    double gamma=0.8;
    double epsilon=0.3;

    final double dMINLearnRate = 0.05;
    final double dDecFactorLR = 0.99;
    double dQmax;
    Vector<EstadoAccion> vEstadosAcciones = new Vector<EstadoAccion>();
    EstadoAccion EstadoAccionActual;
    EstadoAccion EstadoAccionAnterior;
    int AccionAnterior; // 0 hit 1 Stand
    int iNewAction;
    private ArrayList<String> jugadas= new ArrayList<String>();
    int hit;
    public QLJugador(String nombre) {
        super(nombre);
        // TODO Auto-generated constructor stub
		/*Maximo numero de veces que se puede llegar a hitear 6 que seria el equivalente a que cada uno tenga
		la mitad de las cartas más bajas */
		/*el 0 es hitear y el 1 es plantarse*/
    }

    public boolean HacerJugada(Mazo mazo){
        hit =getManoRival()-2;
        vGetNewActionQLearning();
        if (iNewAction==0) {
            pedirCarta(mazo);
            return true;
        }
        else{
            plantarse();
            return false;
        }
    }


    public void vGetNewActionQLearning () {
        boolean bFound;
        int iBest=-1, iNumBest=1;
        EstadoAccion oStateAction;
        bFound = false;							// Searching if we already have the state
        for (int i=0; i<vEstadosAcciones.size(); i++) {
            oStateAction = (EstadoAccion) vEstadosAcciones.elementAt(i);
            if (oStateAction.getPuntosJugador()==getPuntos1() && oStateAction.getPuntosRival()==getPuntos2()) {
                EstadoAccionActual = oStateAction;
                bFound = true;
                break;
            }
        }
        // If we didn't find it, then we add it
        if (!bFound) {
            EstadoAccionActual = new EstadoAccion (getPuntos1(), getPuntos2());
            vEstadosAcciones.add (EstadoAccionActual);
        }

        dQmax = 0;
        // Determining the action to get Qmax{a'}
        if (EstadoAccionActual.dValAction[0] > EstadoAccionActual.dValAction[1]) {
            iBest = 0;
            dQmax = EstadoAccionActual.dValAction[0];
        }
        else if ( (EstadoAccionActual.dValAction[0] == EstadoAccionActual.dValAction[1])) {	// If there is another one equal we must select one of them randomly
            iBest = (int) (Math.random() * (double) 2);
            dQmax = EstadoAccionActual.dValAction[iBest];


        } else 		{
            iBest = 1;
            dQmax = EstadoAccionActual.dValAction[1];

        }

        // Adjusting Q(s,a)
        if (EstadoAccionAnterior != null) {
            EstadoAccionAnterior.dValAction[AccionAnterior] +=  alpha * (gamma * dQmax - EstadoAccionAnterior.dValAction[AccionAnterior]);
        }

        if ( (Math.random() > epsilon) ) 			// Using the e-greedy policy to select the best action or any of the rest
            iNewAction = iBest;
        else iNewAction = (int) (Math.random() * (double) 2);

        EstadoAccionAnterior = EstadoAccionActual;				// Updating values for the next time
        AccionAnterior=iNewAction;
        //	  alpha *= dDecFactorLR;						// Reducing the learning rate
        //  if (alpha < dMINLearnRate) alpha = dMINLearnRate;



    }  // from class LearningTools		}


    //Ajustar Valores Q(s,a)
    public void Refuerzo(int R){
        if (EstadoAccionAnterior != null) {
            EstadoAccionAnterior.dValAction[AccionAnterior] +=  alpha * (R + gamma * dQmax - EstadoAccionAnterior.dValAction[AccionAnterior]);
        }
		/*	alpha *= dDecFactorLR;						// Reducing the learning rate
		if (alpha < dMINLearnRate) alpha = dMINLearnRate;*/
		/*		EstadoAccionAnterior = EstadoAccionActual;				// Updating values for the next time
		AccionAnterior=iNewAction;*/

    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }



}
