package com.kel1.lindungilansia;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ElderActivity extends AppCompatActivity implements SensorEventListener{

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Sensor
    private SensorManager sm;
    private Sensor senAccel;
    private TextView tvHasil;
    private FusedLocationProviderClient fusedLocationClient;

    // Shared Preferences
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        String TAG = "debug_kel1";

        tvHasil = findViewById(R.id.tvHasil);

        //cek apakah sensor tersedia
        sm = (SensorManager)    getSystemService(getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (senAccel != null){
            Log.d(TAG,"Sukses, device punya sensor accelerometer!");
        }
        else {
            // gagal, tidak ada sensor accelerometer.
            Log.d(TAG,"Tidak ada sensor accelerometer!");
        }

        // Logout
//        Button btnLogoutCheck = findViewById(R.id.btnElderHomeLogout);
//        btnLogoutCheck.setOnClickListener(view -> {
//            mAuth.signOut();
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
//        });
        ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        // Permission diberikan
                        Log.d("debug_kel1_map", "permission diberikan");
                        // langsung ambil lokasi
                        ambilLoc(null);
                    } else {
                        //berikan info ke user karena permission tidak diberikan maka fitur jadi tidak tersedia
                        Log.d("debug_kel1_map", "permission TIDAK diberikan");
                    }
                });
        requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
        );
    }

    public void ambilLoc(View v){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //cek permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //belum dapat permission, launch request

        } else {
            //sudah dapat permission
            fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location loc) {
                        // ambil last known location.  bisa null
                        if (loc != null) {
                            float flLatitude = (float) loc.getLatitude();
                            float flLongitude = (float) loc.getLongitude();
                            String latitude = String.valueOf(loc.getLatitude());
                            String longitude = String.valueOf(loc.getLongitude());
                            Log.d("debug_kel1_map", latitude + " " + longitude );

                            //
                            sp = getSharedPreferences("com.kel1.lindungilansia.sp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed;
                            ed = sp.edit();
                            ed.putFloat("currLatitude", flLatitude);
                            ed.putFloat("currLongitude", flLongitude);
                            ed.commit();
                        } else {
                            Log.d("debug_kel1_map", "loc return NULL");
                        }
                    }
                });
        }

    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        double ax=0,ay=0,az=0;
        boolean isTabrakan = false;
        // menangkap perubahan nilai sensor
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            ax=sensorEvent.values[0];
            ay=sensorEvent.values[1];
            az=sensorEvent.values[2];
        }
        if  (az<=-11) {
            isTabrakan = true;
        }
        if (isTabrakan) {
            tvHasil.setText("Terjadi Tabrakan!");
           // Navigation.findNavController(this, R.id.fcvElder).navigate(R.id.action_elderHomeFragment_to_meminta_BantuanFragment);
        } else{
            long timestamp = System.currentTimeMillis();
            // Menampilkan log dari accelerometer beserta timestamp
            String msg = "X: " + ax + ", Y: " + ay + ", Z: " + az + ", Timestamp: " + timestamp;
            Log.d("debug_kel1", msg);

            tvHasil.setText(msg);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

}