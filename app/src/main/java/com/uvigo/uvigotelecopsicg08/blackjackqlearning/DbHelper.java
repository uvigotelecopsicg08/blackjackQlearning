package com.uvigo.uvigotelecopsicg08.blackjackqlearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juani on 29/12/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_black= "black.sqlite";
    private static final int DB_SCHEME_VERSION=1;
    public DbHelper(Context context) {
        super(context, DB_black, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(DataBaseManager.CREATE_TABLE);
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
