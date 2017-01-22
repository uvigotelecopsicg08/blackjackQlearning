package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class play_screen extends AppCompatActivity {
    Partida partida = null;
    ImageView nuevaCartaJugador = null;
    ImageView nuevaCartaAgente = null;
    ArrayList<ImageView> cartasPedidasJugador = null;
    ArrayList<ImageView> cartasPedidasAgente = null;
    boolean rondaAcabada = false;
    TextView mensajeFinRonda;
    ArrayList<Carta> manoAgente = null;
    int opcion;
    MediaPlayer mediaPlayer;
    boolean music=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Base de datos

        this.setContentView(R.layout.activity_play_screen);
        View v= (View) findViewById(R.id.activity_play_screen);
        Context context = getApplicationContext();
        mediaPlayer = MediaPlayer.create(context, R.raw.tetris);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {

            opcion = extras.getInt("OPCION");
            if (opcion == 0) {
                Toast.makeText(getApplicationContext(), "Iniciar nueva partida", Toast.LENGTH_LONG).show();
               // partida = new Partida();
                try{
                    ObjectInputStream ois = new ObjectInputStream(context.openFileInput("partidaNew.txt"));
                    partida =(Partida) ois.readObject();


                }catch (IOException e){
                    partida =new Partida(context);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                loadParameters(partida,v);
                partida.nuevaRonda();

                ImageView cartaAgente1 = (ImageView) findViewById(R.id.cartaAgente1);
                ImageView cartaAgente2 = (ImageView) findViewById(R.id.cartaAgente2);
                manoAgente = partida.getManoAgente();
                cartaAgente1.setImageResource(manoAgente.get(0).getCara());
                cartaAgente2.setImageResource(manoAgente.get(1).getCara());
                ImageView cartaJugador1 = (ImageView) findViewById(R.id.cartaJugador1);
                ImageView cartaJugador2 = (ImageView) findViewById(R.id.cartaJugador2);
                ArrayList<Carta> manoJugador = partida.getManoJugador();
                cartaJugador1.setImageResource(manoJugador.get(0).getCara());
                cartaJugador2.setImageResource(manoJugador.get(1).getCara());
                mostrarPuntos();
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
                if (partida.findeRonda()) {
                    finalRonda();
                }


            } else {
                partida = (Partida) loadSerializedObject();
                LinearLayout linearLayoutJugador = (LinearLayout) findViewById(R.id.linearLayoutJugador);
                LinearLayout linearLayoutAgente = (LinearLayout) findViewById(R.id.linearLayoutAgente);
                loadParameters(partida,v);
                Toast.makeText(getApplicationContext(), "Continuar con la partida", Toast.LENGTH_LONG).show();
                manoAgente = new ArrayList<Carta>();
                ImageView cartaAgente1 = (ImageView) findViewById(R.id.cartaAgente1);
                ImageView cartaAgente2 = (ImageView) findViewById(R.id.cartaAgente2);
                manoAgente = partida.getManoAgente();
                cartaAgente1.setImageResource(manoAgente.get(0).getCara());
                cartaAgente2.setImageResource(manoAgente.get(1).getCara());

                ImageView cartaJugador1 = (ImageView) findViewById(R.id.cartaJugador1);
                ImageView cartaJugador2 = (ImageView) findViewById(R.id.cartaJugador2);
                ArrayList<Carta> manoJugador = partida.getManoJugador();
                cartaJugador1.setImageResource(manoJugador.get(0).getCara());
                cartaJugador2.setImageResource(manoJugador.get(1).getCara());
                mostrarPuntos();
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
                for (int i = 2; i < manoJugador.size() && manoJugador.size() > 2; i++) {
                    RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaJugador.getLayoutParams();
                    LinearLayout.LayoutParams nuevosParams = (LinearLayout.LayoutParams) findViewById(R.id.cartaJugador2).getLayoutParams();

                    nuevaCartaJugador.setImageResource(partida.getManoJugador().get(i).getCara());  // Cambio de codigo con respecto el de gabri
                    nuevaCartaJugador.setId(View.generateViewId());
                    cartasPedidasJugador.add(nuevaCartaJugador);
                    nuevaCartaJugador.setLayoutParams(nuevosParams);
                    layout.removeView(nuevaCartaJugador);
                    linearLayoutJugador.addView(nuevaCartaJugador, nuevosParams);
                    nuevaCartaJugador = new ImageView(this);
                    nuevaCartaJugador.setImageResource(R.drawable.back);
                    layout.addView(nuevaCartaJugador, viejosParams);
                }
                for (int i = 2; i < manoAgente.size() && manoJugador.size() > 2; i++) {
                    RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaAgente.getLayoutParams();
                    LinearLayout.LayoutParams nuevosParams = (LinearLayout.LayoutParams) findViewById(R.id.cartaAgente2).getLayoutParams();
                    /*if (cartasPedidasAgente.isEmpty()) {
                        nuevosParams.addRule(RelativeLayout.RIGHT_OF, R.id.cartaAgente2);
                    } else {
                        nuevosParams.addRule(RelativeLayout.RIGHT_OF, cartasPedidasAgente.get(i).getId());
                    }
                    nuevosParams.setMargins(50, 0, 0, 0);
                    nuevaCartaAgente.setLayoutParams(nuevosParams);*/
                    layout.removeView(nuevaCartaAgente);
                    linearLayoutAgente.addView(nuevaCartaAgente, nuevosParams);
                    nuevaCartaAgente.setId(View.generateViewId());
                    cartasPedidasAgente.add(nuevaCartaAgente);
                    nuevaCartaAgente = new ImageView(this);
                    nuevaCartaAgente.setImageResource(R.drawable.back);
                    layout.addView(nuevaCartaAgente, viejosParams);
                }
                if (partida.findeRonda()) {
                    finalRonda();
                    revelarCartasAgente();
                }
            }


        }
    }

    public void onClickHit(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        LinearLayout linearLayoutAgente = (LinearLayout) findViewById(R.id.linearLayoutAgente);
        LinearLayout linearLayoutJugador = (LinearLayout) findViewById(R.id.linearLayoutJugador);
//        TransitionManager.beginDelayedTransition(linearLayout);
        TransitionManager.beginDelayedTransition(layout);
        if (!partida.getAgente().isPlantado()) {
            if (partida.jugadaAgente()) {
                RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaAgente.getLayoutParams();
                LinearLayout.LayoutParams nuevosParams = (LinearLayout.LayoutParams) findViewById(R.id.cartaAgente2).getLayoutParams();

                nuevaCartaAgente.setId(View.generateViewId());
                cartasPedidasAgente.add(nuevaCartaAgente);
                layout.removeView(nuevaCartaAgente);
                linearLayoutAgente.addView(nuevaCartaAgente, nuevosParams);
                nuevaCartaAgente = new ImageView(this);
                nuevaCartaAgente.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaAgente, viejosParams);

            }

        }
        partida.pedirJugador();
        RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaJugador.getLayoutParams();
        LinearLayout.LayoutParams nuevosParams = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        layout.removeView(nuevaCartaJugador);
        ImageView cartax = (ImageView) findViewById(R.id.cartaJugador2);
        LinearLayout.LayoutParams paramsCartaJugador = (LinearLayout.LayoutParams) cartax.getLayoutParams();
        nuevaCartaJugador.setLayoutParams(paramsCartaJugador);
        linearLayoutJugador.addView(nuevaCartaJugador, paramsCartaJugador);
        nuevaCartaJugador.setImageResource(partida.getManoJugador().get(partida.getManoJugador().size() - 1).getCara());
        nuevaCartaJugador.setId(View.generateViewId());
        cartasPedidasJugador.add(nuevaCartaJugador);
        nuevaCartaJugador = new ImageView(this);
        nuevaCartaJugador.setImageResource(R.drawable.back);
        layout.addView(nuevaCartaJugador, viejosParams);
        partida.ActualizarPuntos(partida.getJugador(), partida.getAgente());
        mostrarPuntos();
        if (partida.findeRonda()) {
            finalRonda();
            revelarCartasAgente();
        }
    }

    public void onClickStand(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        LinearLayout linearLayoutAgente = (LinearLayout) findViewById(R.id.linearLayoutAgente);
        TransitionManager.beginDelayedTransition(layout);
        partida.getJugador().plantarse();
        while (!partida.getAgente().isPlantado() && partida.getPuntosAgenteRonda()<21) {


            if (partida.jugadaAgente()) {
                RelativeLayout.LayoutParams viejosParams = (RelativeLayout.LayoutParams) nuevaCartaAgente.getLayoutParams();
                LinearLayout.LayoutParams nuevosParams = (LinearLayout.LayoutParams) findViewById(R.id.cartaAgente2).getLayoutParams();
                /*if (cartasPedidasAgente.isEmpty()) {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, R.id.cartaAgente2);
                } else {
                    nuevosParams.addRule(RelativeLayout.RIGHT_OF, cartasPedidasAgente.get(cartasPedidasAgente.size() - 1).getId());
                }
                nuevosParams.setMargins(50, 0, 0, 0);*/
//                nuevaCartaAgente.setLayoutParams(nuevosParams);
                nuevaCartaAgente.setId(View.generateViewId());
                cartasPedidasAgente.add(nuevaCartaAgente);
                layout.removeView(nuevaCartaAgente);
                linearLayoutAgente.addView(nuevaCartaAgente, nuevosParams);
                nuevaCartaAgente = new ImageView(this);
                nuevaCartaAgente.setImageResource(R.drawable.back);
                layout.addView(nuevaCartaAgente, viejosParams);

            }

            /*if (partida.jugadaAgente()) {
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

            }*/
            partida.ActualizarPuntos(partida.getJugador(), partida.getAgente());
            mostrarPuntos();
        }
        TransitionManager.endTransitions(layout);
        finalRonda();
        revelarCartasAgente();


    }

    public void revelarCartasAgente() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        TransitionManager.beginDelayedTransition(layout);
        ImageView cartaAgente;
        for (int i = 0; i < cartasPedidasAgente.size(); i++) {
            cartaAgente = cartasPedidasAgente.get(i);
            cartaAgente.setImageResource(partida.getManoAgente().get(i + 2).getCara());
        }
    }

    public void finalRonda() {
        mostrarPuntosFinRonda();
        partida.recuentoPuntos();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.BELOW, R.id.scrollviewAgente);
//        p.addRule(RelativeLayout.CENTER_VERTICAL);
        p.setMargins(0,150,0,0);
        mensajeFinRonda = new TextView(this);
        mensajeFinRonda.setText("Agente: " + partida.getPuntosAgenteRonda() + " Tú: " + partida.getPuntosJugadorRonda());
        mensajeFinRonda.setLayoutParams(p);
        layout.addView(mensajeFinRonda);
        findViewById(R.id.hitButton).setEnabled(false);
        findViewById(R.id.standButton).setEnabled(false);
        partida.añadirRondaJugada();
        rondaAcabada = true;
    }

    public void mostrarPuntosFinRonda(){
        TextView puntosAgente = (TextView) findViewById(R.id.puntosAgente);
        puntosAgente.setText("Agente: "+ partida.getPuntosAgenteRonda());
        TextView puntosJugador = (TextView) findViewById(R.id.puntosUsuario);
        puntosJugador.setText("Tú: " + partida.getPuntosJugadorRonda());

    }
    public void mostrarPuntos(){
        TextView puntosAgente = (TextView) findViewById(R.id.puntosAgente);
        puntosAgente.setText("Agente: "+ partida.getJugador().getPuntos2());
        TextView puntosJugador = (TextView) findViewById(R.id.puntosUsuario);
        puntosJugador.setText("Tú: " + partida.getPuntosJugadorRonda());
    }

    public void nuevaRonda() {
        findViewById(R.id.hitButton).setEnabled(true);
        findViewById(R.id.standButton).setEnabled(true);
        LinearLayout linearLayoutAgente = (LinearLayout) findViewById(R.id.linearLayoutAgente);
        LinearLayout linearLayoutJugador = (LinearLayout) findViewById(R.id.linearLayoutJugador);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_play_screen);
        layout.removeView(mensajeFinRonda);
        for (ImageView carta : cartasPedidasAgente) {
            linearLayoutAgente.removeView(carta);
        }
        for (ImageView carta : cartasPedidasJugador) {
            linearLayoutJugador.removeView(carta);
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
        mostrarPuntos();
        if (partida.findeRonda()) {
            finalRonda();
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (partida.checkNumRondas()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            DataBaseManager  dbM =new DataBaseManager(this);
            dbM.insertar(partida.getPuntosAgente(),partida.getPuntosJugador());
            dbM.close();
            if(partida.getPuntosAgente() > partida.getPuntosJugador()){
                alertDialogBuilder.setTitle("Has perdido");
            }else if(partida.getPuntosAgente() == partida.getPuntosJugador()){
                alertDialogBuilder.setTitle("Empate");
            }else {
                alertDialogBuilder.setTitle("¡Has ganado!");
            }
            String mensaje = "Resultado final:\nAgente: "+partida.getPuntosAgente()+" Tú: "+ partida.getPuntosJugador();
            mensaje += "\n¿Quieres jugar otra partida?";

            // set dialog message
            alertDialogBuilder
                    .setMessage(mensaje)
                    .setCancelable(false)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Empezará otra partida nueva
                            nuevaRonda();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Voverá al menú principal
                       // play_screen.this.finish();
                            Intent intent = new Intent(play_screen.this, home_screen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("iniciada", false);
                            startActivity(intent);

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            partida.partidaNueva();
        } else {
            if (rondaAcabada) {
                rondaAcabada = false;
                nuevaRonda();
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override

    protected void onStart() {
        super.onStop();

    }

    protected void onStop() {
        super.onStop();

    }

    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

        saveObject(partida);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected void onRestart() {
        if(music) {
            mediaPlayer.start();
        }
        super.onRestart();

    }

    public void saveObject(Partida p) {
        try {
            Context context = getApplicationContext();
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("partida.txt", Context.MODE_PRIVATE)); //Select where you wish to save the file...
            oos.writeObject(p); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
            oos.close();// close the stream
            p.saveMatrizQ(context);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public Object loadSerializedObject() {
        try {
            Context context = getApplicationContext();
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("partida.txt"));
            Object o = ois.readObject();
            return o;
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;
    }
    public void  loadParameters(Partida p,View v){
        try{
            Context context = getApplicationContext();
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("parameters.txt"));
           Parameters parameters =(Parameters) ois.readObject();
            System.out.println("Ya hay parametros");
            partida.setNumRondas(parameters.getNumRondas());
            if(parameters.getColor().equals("Rojo")) {
                v.setBackgroundColor(Color.parseColor("#CC0000"));
            }
            else if (parameters.getColor().equals("Verde")){
                v.setBackgroundColor(Color.parseColor("#009900"));
            }
            else if (parameters.getColor().equals("Amarillo")){
               v.setBackgroundColor(Color.parseColor("#FFCC00"));
            }
            else if (parameters.getColor().equals("Negro")){
                v.setBackgroundColor(Color.parseColor("#000000"));
            }
            else if (parameters.getColor().equals("Blanco")){
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            if(parameters.isMusic()){
                mediaPlayer.start();
                music=true;
            }
            if(parameters.isFacil()){
                partida.setEpsilon(0.5);
                System.out.println("Esta pulsado facil");
            }
            else{
                partida.setEpsilon(0.10);
                System.out.println("no esta pulsado facil");
            }

        }catch (IOException e){
            System.out.println("No hay parametros");
        } catch (ClassNotFoundException e) {
            System.out.println("No hay parametros");
        }
    }

}
