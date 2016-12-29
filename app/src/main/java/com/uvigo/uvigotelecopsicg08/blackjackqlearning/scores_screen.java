package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

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
        String[] from = new String[]{manager.CN_MACHINE_SCORE, manager.CN_PLAYER_SCORE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
       // adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
       // lista.setAdapter(adapter);
        TodoCursorAdapter adapter= new TodoCursorAdapter(this,cursor);
        lista.setAdapter(adapter);
    }
}

