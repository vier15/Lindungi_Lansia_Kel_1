package com.kel1.lindungilansia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbLindungiLansia.db";
    public static final String TABLE_CREATE =
            "CREATE TABLE USER (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, TELEPON  TEXT, EMAIL TEXT, PASSWORD TEXT, TANGGAL_LAHIR DATE)";

    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create DB
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //jika app diupgrade (diinstall yang baru)
        //maka database akan dicreate ulang (data hilang)
        //kalau mau  data ingin hilang, bisa diproses disini (migrasi)

        db.execSQL("DROP TABLE IF EXISTS USER");
    }
}
