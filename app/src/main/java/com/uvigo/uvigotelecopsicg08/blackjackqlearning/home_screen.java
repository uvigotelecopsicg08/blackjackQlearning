package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import android.content.Intent;
import android.widget.Toast;

public class home_screen extends AppCompatActivity  implements View.OnClickListener  {
    Button buttonNewPlay,buttonContinue,buttonQuit,buttonScores,buttonSetings;
    boolean partidaIniciada=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        buttonNewPlay=(Button)findViewById(R.id.buttonNewPlay);
        buttonContinue=(Button)findViewById(R.id.buttonContinue);
        buttonQuit=(Button)findViewById(R.id.buttonQuit);
        buttonScores=(Button)findViewById(R.id.buttonScores);
        buttonSetings=(Button)findViewById(R.id.buttonSettings);
        buttonNewPlay.setOnClickListener(this);
        buttonContinue.setOnClickListener(this);
        buttonSetings.setOnClickListener(this);
        buttonScores.setOnClickListener(this);
        buttonQuit.setOnClickListener(this);
    }
    public void onClick(View v) {
        int opcion;
       Intent intent;
        switch (v.getId()) {
            case R.id.buttonNewPlay:
                // Toast.makeText(getApplicationContext(),"prueba fenomeno",Toast.LENGTH_LONG).show();
                 opcion = 0;
                partidaIniciada=true;
                intent =new Intent(home_screen.this,loading_screen.class);
                //intent.putExtra("OPCION",opcion);
                startActivity(intent);



                break;
            case R.id.buttonContinue:
                if(partidaIniciada) {
                    opcion = 1;
                    intent = new Intent(home_screen.this, play_screen.class);
                    intent.putExtra("OPCION", opcion);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "No hay partida iniciada", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonScores:
                intent =new Intent(home_screen.this,scores_screen .class);
                startActivity(intent);
                break;
            case R.id.buttonSettings:
                intent =new Intent(home_screen.this,setting_screen .class);
                startActivity(intent);
                break;
            case R.id.buttonQuit:
                System.exit(0);
                break;

            default:
                break;
        }
    }

}
