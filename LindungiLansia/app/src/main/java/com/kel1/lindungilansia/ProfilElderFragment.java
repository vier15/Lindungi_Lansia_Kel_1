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
 * Use the {@link ProfilElderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilElderFragment extends Fragment {

    private DbUser dbuser;
    private CaregiverViewModel model;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilElderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilElderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilElderFragment newInstance(String param1, String param2) {
        ProfilElderFragment fragment = new ProfilElderFragment();
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
        View view = inflater.inflate(R.layout.fragment_profil_elder, container, false);

        // Shared preferences
        SharedPreferences sp = getActivity().getSharedPreferences("com.kel1.lindungilansia.sp", Context.MODE_PRIVATE);

        // Membuka database
        dbuser = new DbUser(getActivity().getApplicationContext());
        dbuser.open();

        model = new CaregiverViewModel();
        TextView tvNamaProfilElder = view.findViewById(R.id.tvNamaProfilElder);
        TextView tvEmailProfilElder = view.findViewById(R.id.tvEmailProfilElder);

        // Ambil data user yang login dari database
        String loginEmail = sp.getString("email", "");
        String loginPass = sp.getString("pass", "");
        DbUser.User usr = dbuser.getUserLogin(loginEmail, loginPass);

        // Ubah data viewmodel caregiver
        model.setNama(usr.nama);
        model.setEmail(usr.email);

        tvNamaProfilElder.setText(model.getNama());
        tvEmailProfilElder.setText(model.getEmail());

        // Navigasi ke halaman sunting profil elder
        Button btnSuntingProfilElder = view.findViewById(R.id.btnSuntingProfilElder);
        btnSuntingProfilElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profilElderFragment_to_elderSuntingProfilFragment);
            }
        });

        // Navigasi ke halaman rekam medis
        Button btnRekamMedisElder = view.findViewById(R.id.btnRekamMedisElder);
        btnRekamMedisElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profilElderFragment_to_elderRekamMedisFragment);
            }
        });

        // Navigasi ke halaman elder home
        Button btnKembaliProfilElder = view.findViewById(R.id.btnKembaliProfilElder);
        btnKembaliProfilElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profilElderFragment_to_elderHomeFragment);
            }
        });

        dbuser.close();
        return view;
    }
}