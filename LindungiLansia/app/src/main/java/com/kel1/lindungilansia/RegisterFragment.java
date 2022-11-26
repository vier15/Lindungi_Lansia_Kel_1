package com.kel1.lindungilansia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DbUser dbuser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Membuka database
        dbuser = new DbUser(getActivity().getApplicationContext());
        dbuser.open();

        // Membuka shared preferences
        SharedPreferences sp = getActivity().getSharedPreferences("com.kel1.lindungilansia.sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Button btnDaftarRegister = view.findViewById(R.id.btnDaftarRegister);
        btnDaftarRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView etNamaRegister = view.findViewById(R.id.etNamaRegister);
                TextView etEmailRegister = view.findViewById(R.id.etEmailRegister);
                TextView etPassRegister = view.findViewById(R.id.etPassRegister);

                // Validasi text field yang masih kosong
                if (etNamaRegister.getText().toString().length() == 0) {
                    etNamaRegister.setError("Nama masih kosong");
                }
                else if (etEmailRegister.getText().toString().length() == 0) {
                    etEmailRegister.setError("Email masih kosong");
                }
                else if (etPassRegister.getText().toString().length() == 0) {
                    etPassRegister.setError("Password masih kosong");
                }
                else {
                    // Simpan data ke database
                    String nama = etNamaRegister.getText().toString();
                    String email = etEmailRegister.getText().toString();
                    String pass = etPassRegister.getText().toString();
                    dbuser.insertUser(nama, "",email, pass, "");

                    // Ambil data login dari database
                    DbUser.User usr = dbuser.getUserLogin(email, pass);

                    // simpan data user di shared preferences

                    editor.putString("email", usr.email);
                    editor.putString("pass", usr.password);
                    editor.commit();

                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_roleRegisterFragment);
                }
            }
        });

        Button btnBatalRegister = view.findViewById(R.id.btnBatalRegister);
        btnBatalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        return view;
    }

    @Override
    public void onDestroy(){
        dbuser.close();
        super.onDestroy();
    }
}