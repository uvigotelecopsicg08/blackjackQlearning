package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Juani on 29/12/2016.
 */

public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(android.R.layout.two_line_list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView titulo = (TextView) view.findViewById(android.R.id.text1);
        TextView descripcion = (TextView) view.findViewById(android.R.id.text2);
        // Extract properties from cursor
        int machine = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_MACHINE_SCORE));
        int player = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.CN_PLAYER_SCORE));
        Date date = Timestamp.valueOf(cursor.getString(3));
        // Populate fields with extracted properties
        titulo.setText(String.valueOf(date));
        descripcion.setText("Machine: "+ String.valueOf(machine)+" - "+"Player: "+ String.valueOf(player));
    }
}