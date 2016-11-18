package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class play_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        Intent intent = getIntent();
        Bundle extras =intent.getExtras();
        if(extras !=null){
            int opcion = extras.getInt("OPCION");
            if(opcion==0){
                Toast.makeText(getApplicationContext(),"Iniciar nueva partida",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Continuar con la partida",Toast.LENGTH_LONG).show();
            }

        }
    }
}
