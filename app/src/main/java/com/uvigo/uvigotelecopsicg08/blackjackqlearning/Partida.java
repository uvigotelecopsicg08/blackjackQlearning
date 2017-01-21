package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.io.Serializable;

/**
 * Created by Juani on 20/11/2016.
 */

public class Partida implements Serializable {
    //datos de la partida
    private static boolean jugadorListo, agenteListo; //boolean que indica si el jugador/agente ha tomado una decisión
    private static Jugador entrenador, jugadorHumano, jugador;
    private static QLJugador agente;
    private static Mazo mazo;

    public static int getNumRondas() {
        return numRondas;
    }

    public static void setNumRondas(int numRondas) {
        Partida.numRondas = numRondas;
    }

    private static int numRondas, rondasJugadas = 0; //numero de rondas por partida
    private static int puntosJugador = 0, puntosAgente = 0; //numero de rondas ganadas
    private static int puntosJugadorRonda = 0, puntosAgenteRonda = 0;

    public Partida(Context context) {
        jugador = new JugadorHumano("jugador"); //Este jugador seria el interactivo
        agente = new QLJugador("agente");
        jugadorHumano = new JugadorHumano("jugadorHumano");  //Este jugador seria el interactivo
        numRondas=1750;
        mazo=new Mazo();
        if(loadMatrizQ(context)) {
            Train();
        }
        numRondas=10;
        rondasJugadas=0;
        puntosJugador=0;
        puntosAgente=0;
    }
    public static void Train(){
        while(numRondas!=rondasJugadas){
            int in = 0;
            puntosJugadorRonda=0;
            puntosAgenteRonda=0;
            mazo.inicializar();
            mazo.barajar();
            mazo.repartirCartas(jugador, agente);
            ActualizarPuntos(jugador,agente);
            agente.setPuntos2(puntosJugadorRonda);
            jugador.setPuntos2(puntosAgenteRonda);
            for (int i=0;i<jugador.getMano().size();i++){
                //		in = 0;
                //		System.out.println("Cartas en mi mano iniciales:"+jugador.getMano().get(i).getValor());
            }
            while(puntosJugadorRonda<21 && puntosAgenteRonda<21 && !(agente.isPlantado() && jugador.isPlantado())){
				/*			if (in==1 && !agente.isPlantado()){
				agente.Refuerzo(0);
			}*/
                if (!jugador.isPlantado())
                    jugador.HacerJugada(mazo);
                for (int i=0;i<jugador.getMano().size();i++){
                    //			System.out.println("Cartas en mi mano:"+jugador.getMano().get(i).getValor());
                }
                if (!agente.isPlantado())
                    agente.HacerJugada(mazo);

                ActualizarPuntos(jugador,agente);
                in=1;

            }
            //			System.out.println("Jugador="+puntosJugadorRonda+ "Agente="+puntosAgenteRonda);
            recuentoPuntos();
            rondasJugadas++;

        }

        System.out.println("Partida finalizada.");
        System.out.println("Jugador="+puntosJugador+ "Agente="+puntosAgente);

        if(puntosJugador==puntosAgente) System.out.println("Los jugadores han empatado.");
        else if(puntosJugador>puntosAgente) System.out.println("El jugador ha ganado.");
        else System.out.println("El agente ha ganado");
    }







    public void nuevaRonda() {
        puntosJugadorRonda = 0;
        puntosAgenteRonda = 0;
        mazo.inicializar();
        mazo.barajar();
        mazo.repartirCartas(jugador, agente);
        ActualizarPuntos(jugador, agente);
        agente.setPuntos2(puntosJugadorRonda);
        jugador.setPuntos2(puntosAgenteRonda);

    }


    public static void recuentoPuntos() {
        if (agente.isPlantado())
            agente.EstadoAccionAnterior=agente.EstadoAccionActual;
        if (puntosJugadorRonda>puntosAgenteRonda && puntosJugadorRonda<=21){
            puntosJugador++;
            agente.Refuerzo(-10);
            agente.EstadoAccionAnterior=null;
        }
        else if (puntosAgenteRonda>puntosJugadorRonda && puntosAgenteRonda<=21){
            puntosAgente++;
            agente.Refuerzo(10);
            agente.EstadoAccionAnterior=null;

        }
		/*else if(puntosAgenteRonda==puntosJugadorRonda && puntosJugadorRonda<=21)
	{
		puntosJugador++;
		puntosAgente++;

	}*/
        else if (puntosAgenteRonda<21 && puntosJugadorRonda>21){
            agente.Refuerzo(10);
            puntosAgente++;
            agente.EstadoAccionAnterior=null;

        }
        else if (puntosJugadorRonda<21 && puntosAgenteRonda>21){
            agente.Refuerzo(-10);
            puntosJugador++;
            agente.EstadoAccionAnterior=null;

        }	else if (puntosJugadorRonda<21 && puntosAgenteRonda<21 && puntosJugadorRonda==puntosAgenteRonda){
            agente.Refuerzo(5);
            agente.EstadoAccionAnterior=null;

        }
        else if (puntosJugadorRonda>21 && puntosAgenteRonda>21){
            agente.Refuerzo(-10);
            agente.EstadoAccionAnterior=null;

        }
    }

    public static void ActualizarPuntos(Jugador jugador, Jugador agente) {
        int i;
        int AuxPuntos = 0;
        ArrayList<Carta> Aux = jugador.getMano();
        for (i = 0; i < Aux.size(); i++) {
            AuxPuntos = AuxPuntos + ValorCartas(Aux.get(i));

        }
        if (AuxPuntos <= 11 && jugador.hasAs())
            AuxPuntos = AuxPuntos + 10;

        jugador.setPuntos1(AuxPuntos);
        puntosJugadorRonda = AuxPuntos;
        jugador.setManoRival(agente.getMano().size());
        //  agente.setPuntos2(AuxPuntos);

        AuxPuntos = 0;
        Aux = agente.getMano();

        for (i = 0; i < Aux.size(); i++) {
            AuxPuntos = AuxPuntos + ValorCartas(Aux.get(i));

        }
        if (AuxPuntos <= 11 && agente.hasAs())
            AuxPuntos = AuxPuntos + 10;

        agente.setPuntos1(AuxPuntos);
        puntosAgenteRonda = AuxPuntos;
        //  jugador.setPuntos2(AuxPuntos);


    }


    public static int ValorCartas(Carta carta) {

        if (carta.getValor().equals("1"))
            return 1;
        else if (carta.getValor().equals("2"))
            return 2;
        if (carta.getValor().equals("3"))
            return 3;
        else if (carta.getValor().equals("4"))
            return 4;
        if (carta.getValor().equals("5"))
            return 5;
        else if (carta.getValor().equals("6"))
            return 6;
        if (carta.getValor().equals("7"))
            return 7;
        else if (carta.getValor().equals("8"))
            return 8;
        if (carta.getValor().equals("9"))
            return 9;
        else return 10;
    }

    public Jugador getAgente() {
        return agente;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ArrayList<Carta> getManoAgente() {
        return agente.getMano();
    }

    public ArrayList<Carta> getManoJugador() {
        return jugador.getMano();
    }

    public boolean jugadaAgente() {
        return agente.HacerJugada(mazo);
    }

    public void pedirJugador() {
        jugador.pedirCarta(mazo);
    }

    public boolean findeRonda() {
        if (puntosJugadorRonda < 21 && puntosAgenteRonda < 21 && !(agente.isPlantado() && jugador.isPlantado())) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkNumRondas() {
        if (rondasJugadas >= numRondas) {
            return true;
        } else {
            return false;
        }
    }

    public void añadirRondaJugada(){
        rondasJugadas++;
    }

    public int getPuntosJugadorRonda() {
        return puntosJugadorRonda;
    }

    public int getPuntosAgenteRonda() {
        return puntosAgenteRonda;
    }

    public void partidaNueva(){
        rondasJugadas = 0;
        puntosJugador = 0;
        puntosAgente = 0;
    }
    public int getPuntosAgente() {
        return puntosAgente;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }
    public void saveMatrizQ(Context context) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("agenteQL.txt", Context.MODE_PRIVATE)); //Select where you wish to save the file...
            oos.writeObject(agente); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
            oos.close();// close the stream
            System.out.println("Se ha guardado la matriz Q ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar la matriz Q");
        }
    }
    public boolean loadMatrizQ(Context context){
        try {
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("agenteQL.txt"));
            agente = (QLJugador) ois.readObject();
            System.out.println("Se ha cargado el agente");
            return false;

        }catch (IOException e){
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }
  public void  setEpsilon(double epsilon){
      agente.setEpsilon(epsilon);
  }

}
