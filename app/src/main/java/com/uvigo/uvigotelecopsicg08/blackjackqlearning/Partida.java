package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import java.util.ArrayList;

/**
 * Created by Juani on 20/11/2016.
 */

public class Partida {
    //datos de la partida
    private static boolean jugadorListo, agenteListo; //boolean que indica si el jugador/agente ha tomado una decisión
    private static Jugador entrenador, agente;
    private static JugadorHumano jugador;
    private static Mazo mazo;
    private static int numRondas, rondasJugadas=0; //numero de rondas por partida
    private static int puntosJugador=0, puntosAgente=0; //numero de rondas ganadas
    private static int puntosJugadorRonda=0, puntosAgenteRonda=0;
    public Partida(){
        jugador = new JugadorHumano("jugador"); //Este jugador seria el interactivo
        agente = new Jugador("agente");
        numRondas = 10;
        mazo = new Mazo();
    }

    public void nuevaRonda(){
        puntosJugadorRonda = 0;
        puntosAgenteRonda = 0;
        mazo.inicializar();
        mazo.barajar();
        mazo.repartirCartas(jugador, agente);
        ActualizarPuntos(jugador, agente);
        agente.setPuntos2(puntosJugadorRonda);
        jugador.setPuntos2(puntosAgenteRonda);

    }
    public void main(String[] args) {
        //inicializamos partida
//		jugador = new Jugador("jugador"); //Esta Jugador es el basico con el behavioral basico
        jugador = new JugadorHumano("jugador"); //Este jugador seria el interactivo
        agente = new Jugador("agente");
        numRondas = 10;
        mazo = new Mazo();
        while (numRondas != rondasJugadas) {
            puntosJugadorRonda = 0;
            puntosAgenteRonda = 0;
            mazo.inicializar();
            mazo.barajar();
            mazo.repartirCartas(jugador, agente);
            ActualizarPuntos(jugador, agente);
            agente.setPuntos2(puntosJugadorRonda);
            jugador.setPuntos2(puntosAgenteRonda);

            while (puntosJugadorRonda < 21 && puntosAgenteRonda < 21 && !(agente.isPlantado() && jugador.isPlantado())) {
                if (!jugador.isPlantado())
                    jugador.HacerJugada(mazo);
                if (!agente.isPlantado())
                    agente.HacerJugada(mazo);
                ActualizarPuntos(jugador, agente);
            }
            System.out.println("Jugador=" + puntosJugadorRonda + "Agente=" + puntosAgenteRonda);
            RecuentoPuntos();
            rondasJugadas++;

        }

        System.out.println("Partida finalizada.");
        System.out.println("Jugador=" + puntosJugador + "Agente=" + puntosAgente);

        if (puntosJugador == puntosAgente) System.out.println("Los jugadores han empatado.");
        else if (puntosJugador > puntosAgente) System.out.println("El jugador ha ganado.");
        else System.out.println("El agente ha ganado");
    }

    public static void RecuentoPuntos(){
        if (puntosJugadorRonda>puntosAgenteRonda && puntosJugadorRonda<=21)
            puntosJugador++;
        else if (puntosAgenteRonda>puntosJugadorRonda && puntosAgenteRonda<=21)
            puntosAgente++;
    /*else if(puntosAgenteRonda==puntosJugadorRonda && puntosJugadorRonda<=21)
    {
        puntosJugador++;
        puntosAgente++;

    }*/
        else if (puntosAgenteRonda<21 && puntosJugadorRonda>21)
            puntosAgente++;
        else if (puntosJugadorRonda<21 && puntosAgenteRonda>21)
            puntosJugador++;
    }
    public static void ActualizarPuntos(Jugador jugador, Jugador agente){
        int i;
        int AuxPuntos=0;
        ArrayList<Carta> Aux= jugador.getMano();
        for(i=0;i<Aux.size();i++){
            AuxPuntos= AuxPuntos + ValorCartas(Aux.get(i));

        }
        if (AuxPuntos<=11 && jugador.hasAs())
            AuxPuntos=AuxPuntos+10;

        jugador.setPuntos1(AuxPuntos);
        puntosJugadorRonda=AuxPuntos;
        jugador.setManoRival(agente.getMano().size());
        //  agente.setPuntos2(AuxPuntos);

        AuxPuntos=0;
        Aux= agente.getMano();

        for(i=0;i<Aux.size();i++){
            AuxPuntos= AuxPuntos + ValorCartas(Aux.get(i));

        }
        if (AuxPuntos<=11 && agente.hasAs())
            AuxPuntos=AuxPuntos+10;

        agente.setPuntos1(AuxPuntos);
        puntosAgenteRonda=AuxPuntos;
        //  jugador.setPuntos2(AuxPuntos);


    }





    public static  int ValorCartas(Carta carta){

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

    public Jugador getAgente(){
        return agente;
    }
    public Jugador getJugador(){
        return jugador;
    }

    public ArrayList<Carta> getManoAgente(){
        return agente.getMano();
    }
    public ArrayList<Carta> getManoJugador(){
        return jugador.getMano();
    }
    public boolean jugadaAgente(){
        return agente.HacerJugada(mazo);
    }
    public void pedirJugador(){
        jugador.pedirCarta(mazo);
    }
    public boolean findeRonda(){
        if(puntosJugadorRonda < 21 && puntosAgenteRonda < 21 && !(agente.isPlantado() && jugador.isPlantado())){
            return false;
        }else{
            return true;
        }
    }
    public int getPuntosJugadorRonda(){
        return puntosJugadorRonda;
    }
    public int getPuntosAgenteRonda(){
        return puntosAgenteRonda;
    }
}
