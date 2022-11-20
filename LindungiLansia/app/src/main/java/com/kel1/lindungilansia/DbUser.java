package com.kel1.lindungilansia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class DbUser {

    //class untuk menyimpan record
    public static class User {
        public String nama;
        public String telepon;
        public String email;
        public String password;
        public String tanggal_lahir;
    }

    private SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbUser(Context c) {
        dbHelper =  new OpenHelper(c);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertUser(String nama, String noTelp, String email, String password, String tanggal_lahir) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAMA", nama);
        newValues.put("TELEPON", noTelp);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        newValues.put("TANGGAL_LAHIR", String.valueOf(tanggal_lahir));
        return db.insert("USER", null, newValues);
    }

    //ambil data user berdasarkan nama
    public User getUser(String nama) {
        Cursor cur = null;
        User M = new User();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "NAMA", "TELEPON", "EMAIL", "PASSWORD", "TANGGAL_LAHIR"};
        //parameter, akan mengganti ? pada NAMA=?
        String[] param  = {nama};

        //cursor
        cur = db.query("USER",cols,"NAMA=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.nama = cur.getString(1);
            M.telepon = cur.getString(2);
            M.email = cur.getString(3);
            M.password = cur.getString(4);
            M.tanggal_lahir = cur.getString(5);
        }
        cur.close();
        return M;
    }

    //ambil data user berdasarkan email dan password
    public User getUserLogin(String email, String pass) {
        Cursor cur = null;
        User M = new User();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "NAMA", "TELEPON", "EMAIL", "PASSWORD", "TANGGAL_LAHIR"};
        //parameter, akan mengganti ? pada NAMA=?
        String[] param  = {email, pass};

        //cursor
        cur = db.query("USER",cols,"EMAIL=? AND PASSWORD=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.nama = cur.getString(1);
            M.telepon = cur.getString(2);
            M.email = cur.getString(3);
            M.password = cur.getString(4);
            M.tanggal_lahir = cur.getString(5);
        }
        cur.close();
        return M;
    }

}

