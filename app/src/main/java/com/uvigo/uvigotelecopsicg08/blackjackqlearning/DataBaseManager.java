package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by Juani on 29/12/2016.
 */

public class DataBaseManager {
    private SQLiteDatabase db;
    public static final String TABLE_NAME="RESULTADOS";
    public static final String CN_ID = "_id";
    public static final String CN_MACHINE_SCORE="machineScore";
    public static final String CN_PLAYER_SCORE="playerScore";
    public static final String CN_DATE = "fecha";
    public static final String CREATE_TABLE ="create table "+TABLE_NAME+" ("+CN_ID+
            " integer primary key autoincrement,"
            +CN_MACHINE_SCORE+ " integer NOT NULL,"
            +CN_PLAYER_SCORE+" integer NOT NULL,"
            +CN_DATE+" timestamp  DEFAULT CURRENT_TIMESTAMP);";

    public DataBaseManager(Context context) {
        DbHelper helper=new DbHelper(context);
         db= helper.getWritableDatabase();
    }
    public void insertar (int machineScore,int playerScore){
        ContentValues valores =new ContentValues();
        valores.put(CN_MACHINE_SCORE,machineScore);
        valores.put(CN_PLAYER_SCORE,playerScore);
        db.insert(TABLE_NAME,null,valores);
    }
    public Cursor cargarCursor(){
        String columnas[] = new String[]{CN_ID,CN_MACHINE_SCORE,CN_PLAYER_SCORE,CN_DATE};
       return db.query(TABLE_NAME,columnas,null,null,null,null,null,null);
    }
}
