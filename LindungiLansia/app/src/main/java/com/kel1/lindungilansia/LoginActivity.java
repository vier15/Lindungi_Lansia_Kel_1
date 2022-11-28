package com.kel1.lindungilansia;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmailLogin,etPassLogin;
    private Button btnDaftarLogin,btnMasukLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);


        mAuth = FirebaseAuth.getInstance();
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPassLogin = findViewById(R.id.etPassLogin);
        btnDaftarLogin = findViewById(R.id.btnDaftarLogin);
        btnMasukLogin = findViewById(R.id.btnMasukLogin);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        btnDaftarLogin.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });

        btnMasukLogin.setOnClickListener(v->{
           if(etEmailLogin.getText().length()>0 && etPassLogin.getText().length()>0){
                login(etEmailLogin.getText().toString(), etPassLogin.getText().toString());
           }
           else{
               Toast.makeText(getApplicationContext(), "Data belum lengkap", Toast.LENGTH_SHORT).show();
           }
        });
    }


    private void login(String email, String password){
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult()!=null){
                    if(task.getResult().getUser()!=null){
                        reload();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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