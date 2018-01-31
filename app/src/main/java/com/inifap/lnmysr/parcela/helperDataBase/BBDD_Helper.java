package com.inifap.lnmysr.parcela.helperDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis on 23/08/2017.
 */

public class BBDD_Helper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Datos_Parcela.db";

    public BBDD_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Estructura_BBDD.SQL_CREATE_ENTRIES);
        db.execSQL(Estructura_BBDD.SQL_CREATE_RIEGO_DATA);

        ContentValues values = new ContentValues();

        values.put(Estructura_BBDD._ID, 1);
        values.put(Estructura_BBDD.TIPORIEGO, "Goteo");
        db.insert(Estructura_BBDD.TABLE_RIEGO, null,values);
        values.put(Estructura_BBDD._ID, 2);
        values.put(Estructura_BBDD.TIPORIEGO, "Micro aspersión");
        db.insert(Estructura_BBDD.TABLE_RIEGO, null,values);
        values.put(Estructura_BBDD._ID, 3);
        values.put(Estructura_BBDD.TIPORIEGO, "Pivote central avance frontal");
        db.insert(Estructura_BBDD.TABLE_RIEGO, null,values);
        values.put(Estructura_BBDD._ID, 4);
        values.put(Estructura_BBDD.TIPORIEGO, "Superficial");
        db.insert(Estructura_BBDD.TABLE_RIEGO, null,values);
        //db.close();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
        db.execSQL(Estructura_BBDD.SQL_DELETE_RIEGO_DATA);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
