package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class loading_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        System.out.println("LoadingScreenActivity  screen started");
        ProgressBar s = (ProgressBar )findViewById(R.id.mainSpinner1);
        s.setVisibility(View.VISIBLE);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                     sleep(500);  //Delay of 0.5 seconds
                    Context context = getApplicationContext();
                    Partida partida = new Partida(context);
                        ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("partidaNew.txt", Context.MODE_PRIVATE)); //Select where you wish to save the file...
                        oos.writeObject(partida); // write the class as an 'object'
                        oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
                        oos.close();// close the stream


                } catch (Exception e) {

                } finally {

                    int opcion=0;
                    Intent intent = new Intent(loading_screen.this,play_screen.class);
                    intent.putExtra("OPCION",opcion);
                    loading_screen.this.startActivity(intent);
                    loading_screen.this.finish();
                }
            }
        };
        welcomeThread.start();

    }
}
