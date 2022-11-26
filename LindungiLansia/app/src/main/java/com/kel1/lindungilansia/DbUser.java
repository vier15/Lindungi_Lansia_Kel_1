package com.kel1.lindungilansia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class DbUser {

    //class untuk menyimpan record
    public static class User {
        public int id;
        public String nama;
        public String telepon;
        public String email;
        public String password;
        public String tanggal_lahir;
        public String role;
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

    public long insertUser(String nama, String noTelp, String email, String password, String tanggal_lahir, String role) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAMA", nama);
        newValues.put("TELEPON", noTelp);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        newValues.put("TANGGAL_LAHIR", String.valueOf(tanggal_lahir));
        newValues.put("ROLE", role);
        return db.insert("USER", null, newValues);
    }

    public long updateRoleUser(String role, int id, String email){
        String table = "USER";
        ContentValues values = new ContentValues();
        values.put("ROLE", role);
        String whereClause = "ID=? AND EMAIL=?";
        String[] whereArgs = {Integer.toString(id), email};

        return db.update(table, values, whereClause, whereArgs);
    }

    //ambil data user berdasarkan id dan email
    public User getUser(int id, String email) {
        Cursor cur = null;
        User M = new User();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "NAMA", "TELEPON", "EMAIL", "PASSWORD", "TANGGAL_LAHIR", "ROLE"};
        //parameter, akan mengganti ? pada NAMA=?
        String[] param  = {Integer.toString(id), email};

        //cursor
        cur = db.query("USER",cols,"ID=? AND EMAIL=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.id = cur.getInt(0);
            M.nama = cur.getString(1);
            M.telepon = cur.getString(2);
            M.email = cur.getString(3);
            M.password = cur.getString(4);
            M.tanggal_lahir = cur.getString(5);
            M.role = cur.getString(6);
        }
        cur.close();
        return M;
    }

    //ambil data user berdasarkan email dan password
    public User getUserLogin(String email, String pass) {
        Cursor cur = null;
        User M = new User();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "NAMA", "TELEPON", "EMAIL", "PASSWORD", "TANGGAL_LAHIR", "ROLE"};
        //parameter, akan mengganti ? pada NAMA=?
        String[] param  = {email, pass};

        //cursor
        cur = db.query("USER",cols,"EMAIL=? AND PASSWORD=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.id = cur.getInt(0);
            M.nama = cur.getString(1);
            M.telepon = cur.getString(2);
            M.email = cur.getString(3);
            M.password = cur.getString(4);
            M.tanggal_lahir = cur.getString(5);
            M.role = cur.getString(6);
        }
        cur.close();
        return M;
    }

}

