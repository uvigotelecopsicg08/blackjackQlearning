package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class play_screen extends AppCompatActivity {
    Partida partida = null;
    ImageView nuevaCartaJugador = null;
    ImageView nuevaCartaAgente = null;
    ArrayList<ImageView> cartasPedidasJugador = null;
    ArrayList<ImageView> cartasPedidasAgente = null;
    boolean rondaAcabada = false;
    TextView mensajeFinRonda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int opcion = extras.getInt("OPCION");
            if (opcion == 0) {
                Toast.makeText(getApplicationContext(), "Iniciar nueva partida", Toast.LENGTH_LONG).show();
                partida = new Partida();
                partida.nuevaRonda();

                ImageView cartaAgente1 = (ImageView) findViewById(R.id.cartaAgente1);
                ImageView cartaAgente2 = (ImageView) findViewById(R.id.cartaAgente2);
                ArrayList<Carta> manoAgente = partida.getManoAgente();
                cartaAgente1.setImageResource(manoAgente.get(0).getCara());
                cartaAgente2.setImageResource(manoAgente.get(1).getCara());
                ImageView cartaJugador1 = (ImageView) findViewById(R.id.cartaJugador1);
                ImageView cartaJugador2 = (ImageView) findViewById(R.id.cartaJugador2);
                ArrayList<Carta> manoJugador = partida.getManoJugador();
                cartaJugador1.setImageResource(manoJugador.get(0).getCara());
                cartaJugador2.setImageResource(manoJugador.get(1).getCara());

                RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
                ImageView cartaMazo = (ImageView) findViewById(R.id.cartaMazo);
                RelativeLayout.LayoutParams paramsMazo = (RelativeLayout.LayoutParams) cartaMazo.getLayoutParams();
                nuevaCartaJugador = new ImageView(this);
                nuevaCartaJugador.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaJugador, paramsMazo);
                nuevaCartaAgente = new ImageView(this);
                nuevaCartaAgente.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaAgente, paramsMazo);
                cartasPedidasJugador = new ArrayList<ImageView>();
                cartasPedidasAgente = new ArrayList<ImageView>();

            } else {
                Toast.makeText(getApplicationContext(), "Continuar con la partida", Toast.LENGTH_LONG).show();
            }
            /*Jugador agente = new Jugador("Agente");
            Mazo mazo=new Mazo();
            mazo.inicializar();
            mazo.barajar();
            mazo.darCarta(agente);
            ArrayList<Carta> cartasJugador= agente.getMano();
            cartasJugador.get(cartasJugador.size()-1);*/

            /*RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
            ImageView nuevaImagen = new ImageView(this);
            nuevaImagen.setImageResource(R.drawable.back);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.RIGHT_OF, cartaAgente2.getId());
            lp.setMargins(45, 0, 0 ,0);
            layout.addView(nuevaImagen, lp);*/


        }
    }

    public void onClickHit(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        TransitionManager.beginDelayedTransition(layout);
        if (!partida.getAgente().isPlantado()) {
            if (partida.jugadaAgente()) {
                RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaAgente.getLayoutParams();
                RelativeLayout.LayoutParams nuevosParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                if (cartasPedidasAgente.isEmpty()) {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, R.id.cartaAgente2);
                } else {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, cartasPedidasAgente.get(cartasPedidasAgente.size() - 1).getId());
                }
                nuevosParams.setMargins(50, 0, 0, 0);
                nuevaCartaAgente.setLayoutParams(nuevosParams);
                nuevaCartaAgente.setId(View.generateViewId());
                cartasPedidasAgente.add(nuevaCartaAgente);
                nuevaCartaAgente = new ImageView(this);
                nuevaCartaAgente.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaAgente, viejosParams);

            }

        }
        partida.pedirJugador();
        RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaJugador.getLayoutParams();
        RelativeLayout.LayoutParams nuevosParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (cartasPedidasJugador.isEmpty()) {
            nuevosParams.addRule(RelativeLayout.RIGHT_OF, R.id.cartaJugador2);

        } else {
            nuevosParams.addRule(RelativeLayout.RIGHT_OF, cartasPedidasJugador.get(cartasPedidasJugador.size() - 1).getId());
        }
        nuevosParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.cartaJugador1);
        nuevosParams.setMargins(45, 0, 0, 0);
        nuevaCartaJugador.setLayoutParams(nuevosParams);
        nuevaCartaJugador.setImageResource(partida.getManoJugador().get(partida.getManoJugador().size() - 1).getCara());
        nuevaCartaJugador.setId(View.generateViewId());
        cartasPedidasJugador.add(nuevaCartaJugador);
        nuevaCartaJugador = new ImageView(this);
        nuevaCartaJugador.setImageResource(R.drawable.back);
        layout.addView(nuevaCartaJugador, viejosParams);
        partida.ActualizarPuntos(partida.getJugador(), partida.getAgente());
        if (partida.findeRonda()) {
            finalRonda();
            revelarCartasAgente();
        }
    }

    public void onClickStand(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        TransitionManager.beginDelayedTransition(layout);
        partida.getJugador().plantarse();
        while (!partida.getAgente().isPlantado()) {
            if (partida.jugadaAgente()) {
                RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaAgente.getLayoutParams();
                RelativeLayout.LayoutParams nuevosParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                if (cartasPedidasAgente.isEmpty()) {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, R.id.cartaAgente2);
                } else {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, cartasPedidasAgente.get(cartasPedidasAgente.size() - 1).getId());
                }
                nuevosParams.setMargins(50, 0, 0, 0);
                nuevaCartaAgente.setLayoutParams(nuevosParams);
                nuevaCartaAgente.setId(View.generateViewId());
                cartasPedidasAgente.add(nuevaCartaAgente);
                nuevaCartaAgente = new ImageView(this);
                nuevaCartaAgente.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaAgente, viejosParams);

            }
            partida.ActualizarPuntos(partida.getJugador(), partida.getAgente());
        }
        TransitionManager.endTransitions(layout);
        finalRonda();
        revelarCartasAgente();



    }
    public void revelarCartasAgente(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        TransitionManager.beginDelayedTransition(layout);
        ImageView cartaAgente;
        for(int i = 0; i<cartasPedidasAgente.size(); i++){
            cartaAgente = cartasPedidasAgente.get(i);
            cartaAgente.setImageResource(partida.getManoAgente().get(i+2).getCara());
        }
    }
    public void finalRonda(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        mensajeFinRonda = new TextView(this);
        mensajeFinRonda.setText("Agente: " + partida.getPuntosAgenteRonda() + " Tú: " + partida.getPuntosJugadorRonda());
        mensajeFinRonda.setLayoutParams(p);
        layout.addView(mensajeFinRonda);
        findViewById(R.id.hitButton).setEnabled(false);
        rondaAcabada = true;
    }

    public void nuevaRonda(){
        findViewById(R.id.hitButton).setEnabled(true);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        layout.removeView(mensajeFinRonda);
        for(ImageView carta : cartasPedidasAgente){
            layout.removeView(carta);
        }
        for (ImageView carta : cartasPedidasJugador){
            layout.removeView(carta);
        }

        partida.nuevaRonda();
        cartasPedidasJugador.clear();
        cartasPedidasAgente.clear();
        ImageView cartaAgente1 = (ImageView) findViewById(R.id.cartaAgente1);
        ImageView cartaAgente2 = (ImageView) findViewById(R.id.cartaAgente2);
        ArrayList<Carta> manoAgente = partida.getManoAgente();
        cartaAgente1.setImageResource(manoAgente.get(0).getCara());
        cartaAgente2.setImageResource(manoAgente.get(1).getCara());
        ImageView cartaJugador1 = (ImageView) findViewById(R.id.cartaJugador1);
        ImageView cartaJugador2 = (ImageView) findViewById(R.id.cartaJugador2);
        ArrayList<Carta> manoJugador = partida.getManoJugador();
        cartaJugador1.setImageResource(manoJugador.get(0).getCara());
        cartaJugador2.setImageResource(manoJugador.get(1).getCara());

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (rondaAcabada){
            rondaAcabada = false;
            nuevaRonda();
        }
        return super.onTouchEvent(event);
    }
}
