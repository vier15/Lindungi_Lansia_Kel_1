package com.kel1.lindungilansia;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {


    private EditText etNamaRegister, etEmailRegister,etPassRegister;
    private Button btnDaftarRegister, btnBatalRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        etNamaRegister = findViewById(R.id.etNamaRegister);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        etPassRegister = findViewById(R.id.etPassRegister);
        btnDaftarRegister = findViewById(R.id.btnDaftarRegister);
        btnBatalRegister = findViewById(R.id.btnBatalRegister);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        btnDaftarRegister.setOnClickListener(v->{
            if(etNamaRegister.getText().length()>0 && etEmailRegister.getText().length()>0 && etPassRegister.getText().length()>0){
                register(etNamaRegister.getText().toString(),etEmailRegister.getText().toString(), etPassRegister.getText().toString());
            }
            else{
                Toast.makeText(getApplicationContext(), "Data belum lengkap", Toast.LENGTH_SHORT).show();
            }
        });

        btnBatalRegister.setOnClickListener(v -> {
            finish();
        });
    }


    private void register(String nama, String email, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult()!=null) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            if (user!=null){
                                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nama)
                                        .build();

                                Map<String, Object> userData = new HashMap<>();
                                userData.put("nama", nama);
                                userData.put("email", email);
                                db.collection("users").document(user.getUid()).set(userData);

//                                Log.d(TAG, "createUserWithEmail:success");
                                // Jika berhasil request update, pindah ke halaman role register
                                user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(getApplicationContext(), RoleRegisterActivity.class));
                                        finish();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Register Gagal!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @Override
    public void onStart() {
        super.onStart();
        // Cek jika sudah ada user yang login, langsung masuk ke halaman utama
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }


}