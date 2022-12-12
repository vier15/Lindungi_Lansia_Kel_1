package com.kel1.lindungilansia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RoleRegisterActivity extends AppCompatActivity {

    private ImageButton imgBtnElderRegister, imgBtnCaregiverRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_role_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        imgBtnElderRegister = findViewById(R.id.imgBtnElderRegister);
        imgBtnCaregiverRegister = findViewById(R.id.imgBtnCaregiverRegister);
        progressDialog = new ProgressDialog(RoleRegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        // Jika tombol Elder di klik, update role user menjadi Elder
        imgBtnElderRegister.setOnClickListener(view -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateRoleUser(currentUser, "Elder");
        });

        // Jika tombol Caregiver di klik, update role user menjadi Caregiver
        imgBtnCaregiverRegister.setOnClickListener(view -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateRoleUser(currentUser, "Caregiver");
        });

    }

    private void updateRoleUser(FirebaseUser currentUser, String role){
        progressDialog.show();
        Map<String, Object> roleUser = new HashMap<>();
        roleUser.put("role", role);

        db.collection("users").document(currentUser.getUid()).update(roleUser)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Jika proses mengupdate data role user berhasil
                        if(task.isSuccessful()){
                            // Pindah ke halaman utama
                            reload();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Pemilihan role gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        progressDialog.dismiss();
    }

    private void reload(){
        // Ambil role user
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        db.collection("users").whereEqualTo("email", currentUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        // Jika proses ambil data berhasil
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Simpan data user ke hashmap
                                Map<String,Object> data = document.getData();
                                String role = data.get("role").toString();

                                // Jika role user = Elder
                                if(role.equals("Elder")){
                                    startActivity(new Intent(getApplicationContext(), ElderActivity.class));
                                    finish();
                                }
                                else{
                                    startActivity(new Intent(getApplicationContext(), CaregiverActivity.class));
                                    finish();
                                }
                            }
                        } else {
                            Log.w("debug_kel1", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}