package com.kel1.lindungilansia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kel1.lindungilansia.databinding.FragmentCaregiverHomeBinding;

import java.util.Map;

public class CaregiverActivity extends AppCompatActivity {

    private Map<String,Object> data;
    private String nama;
    private TextView tvNama;
    private CaregiverViewModel model;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.fragment_caregiver_home);

        // Setup databinding
        FragmentCaregiverHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.fragment_caregiver_home);
        model = new CaregiverViewModel();
        binding.setLifecycleOwner(this);
        binding.setCaregivermodel(model);

        setContentView(R.layout.fragment_caregiver_home);
        getSupportActionBar().hide();


        // Inisialisasi firebase authentication
        mAuth = FirebaseAuth.getInstance();
        // Inisialisasi firestore
        db = FirebaseFirestore.getInstance();

//        tvNama = findViewById(R.id.tvCaregiverHomeName);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        db.collection("users").whereEqualTo("email", currentUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data = document.getData();
                                nama = data.get("nama").toString();
//                                tvNama.setText(nama);
                                model.setNama("Nama manual tapi dari get");
                            }
                        } else {
                            Log.w("debug_kel1", "Error getting documents.", task.getException());
                        }
                    }
                });

        // Generate token untuk device
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("debug_kel1", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("debug_kel1", token);
//                        Toast.makeText(CaregiverActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


        // Logout
        Button btnCaregiverHomeLogout = findViewById(R.id.btnCaregiverHomeLogout);
        btnCaregiverHomeLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
}