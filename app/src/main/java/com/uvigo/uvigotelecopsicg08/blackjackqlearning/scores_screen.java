package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class scores_screen extends AppCompatActivity {
    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
   // private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_screen);
        lista = (ListView) findViewById(R.id.listView);
        manager = new DataBaseManager(this);
        cursor = manager.cargarCursor();
        if(cursor.getCount()==0){
            System.out.println("El cursor está vacio");
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_scores_screen);
            //LinearLayout linearLayout_empty = (LinearLayout) findViewById(R.id.empty_scores);
            setContentView(R.layout.empty_scores);
          //  TextView texto =new TextView(this);
           /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.listView).getLayoutParams();
            linearLayout .removeView(lista);
            linearLayout.addView(linearLayout_empty ,params);*/
           // texto.setText("No hay datos de partidas");
            //linearLayout.addView(texto,params);
            // System.out.println("Los params "+params.weight+" "+params.width+" "+params.gravity+" "+params.getMarginStart());
          /*  ImageView carta = new ImageView(this);
            carta.setImageResource(R.drawable.chip6);
            linearLayout.addView(carta);*/
        }
        else {
            String[] from = new String[]{manager.CN_MACHINE_SCORE, manager.CN_PLAYER_SCORE};
            int[] to = new int[]{android.R.id.text1, android.R.id.text2};
            TodoCursorAdapter adapter = new TodoCursorAdapter(this, cursor);
            lista.setAdapter(adapter);
        }
    }
    public void onDestroy(){
        manager.close();
        super.onDestroy();

    }
}

