package com.kel1.lindungilansia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DbUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        dbUser = new DbUser(getApplicationContext());
//        dbUser.open();

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = findViewById(R.id.nvElderHomeSidebar);
        navigationView.setItemIconTintList(null);

//        NavController navController = Navigation.findNavController(this, R.id.navhost_main);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onDestroy() {
//        dbUser.close();
        super.onDestroy();
    }


}