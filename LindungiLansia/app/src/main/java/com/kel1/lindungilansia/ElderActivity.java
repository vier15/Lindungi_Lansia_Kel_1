package com.kel1.lindungilansia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ElderActivity extends AppCompatActivity implements SensorEventListener{

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Sensor
    private SensorManager sm;
    private Sensor senAccel;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_elder_home);

        mAuth = FirebaseAuth.getInstance();

        String TAG = "debug_accel_linlun";

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
        Button btnLogoutCheck = findViewById(R.id.btnElderHomeLogout);
        btnLogoutCheck.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
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
        if  (az<=-8) {
            isTabrakan = true;
        }
        if (isTabrakan) {
            tvHasil.setText("Terjadi Tabrakan!");
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