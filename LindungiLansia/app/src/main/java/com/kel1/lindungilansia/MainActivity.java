package com.kel1.lindungilansia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private DbUser dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        dbUser = new DbUser(getApplicationContext());
        dbUser.open();
    }

    @Override
    protected void onDestroy() {
        dbUser.close();
        super.onDestroy();
    }


}