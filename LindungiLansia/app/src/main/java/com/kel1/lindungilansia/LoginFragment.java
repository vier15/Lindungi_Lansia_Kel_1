package com.kel1.lindungilansia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

//import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private DbUser dbuser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Buat shared preferences
        SharedPreferences sp = getActivity().getSharedPreferences("com.kel1.lindungilansia.sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Membuka database
        dbuser = new DbUser(getActivity().getApplicationContext());
        dbuser.open();

//        Login binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
//        model = new ViewModelProvider(this).get(UserViewModel.class);
//        binding.getLifecycle();
//        binding.setViewmodel(model);


        Button btnDaftarLogin = view.findViewById(R.id.btnDaftarLogin);
        btnDaftarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        Button btnMasukLogin = view.findViewById(R.id.btnMasukLogin);
        btnMasukLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmailLogin = view.findViewById(R.id.etEmailLogin);
                EditText etPassLogin = view.findViewById(R.id.etPassLogin);

                // Validasi jika ada field text yang belum diisi
                if (etEmailLogin.getText().toString().length() == 0){
                    etEmailLogin.setError("Email masih kosong");
                }
                else if ((etPassLogin.getText().toString().length() == 0)){
                    etPassLogin.setError("Password masih kosong");
                }
                else {
                    // Mengambil data email
                    String loginEmail = etEmailLogin.getText().toString();
                    String loginPass = etPassLogin.getText().toString();

                    // Ambil data login dari database
                    DbUser.User usr = dbuser.getUserLogin(loginEmail, loginPass);

                    // jika validasi berhasil
                    if(usr.nama != null){
                        // simpan data user di viewmodel

                        editor.putString("email", usr.email);
                        editor.putString("pass", usr.password);
                        editor.commit();

                        // pindah ke halaman home
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_elderHomeFragment);
                    }
                    // jika validasi gagal
                    else{
                        Toast.makeText(getActivity(), "Email atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}