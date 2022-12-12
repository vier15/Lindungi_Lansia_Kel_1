package com.kel1.lindungilansia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kel1.lindungilansia.databinding.FragmentCaregiverHomeBinding;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaregiverHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaregiverHomeFragment extends Fragment {

    private Map<String,Object> data;
    private String nama;
    private CaregiverViewModel model;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaregiverHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaregiverHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaregiverHomeFragment newInstance(String param1, String param2) {
        CaregiverHomeFragment fragment = new CaregiverHomeFragment();
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

        // Data binding
        FragmentCaregiverHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_caregiver_home, container, false);
        View view = binding.getRoot();
        model = new CaregiverViewModel();
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCaregiver(model);

        // Inisialisasi firebase authentication
        mAuth = FirebaseAuth.getInstance();
        // Inisialisasi firestore
        db = FirebaseFirestore.getInstance();

        // Ambil data dari firestore database
        FirebaseUser currentUser = mAuth.getCurrentUser();
        db.collection("users").whereEqualTo("email", currentUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Ambil data nama dan simpan di viewmodel
                                data = document.getData();
                                nama = data.get("nama").toString();
                                model.setNama(nama);
                            }
                        } else {
                            Log.w("debug_kel1", "Error getting documents.", task.getException());
                        }
                    }
                });

        // Navigasi ke lihat profil elder
        ConstraintLayout clElderCaregiverHome1 = view.findViewById(R.id.clElderCaregiverHome1);
        clElderCaregiverHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_caregiverHomeFragment_to_caregiverLihatProfilElderFragment);
            }
        });

        // Navigasi ke demo notifikasi elder butuh bantuan
        Button btnCaregiverHomeDemoNotifElder = view.findViewById(R.id.btnCaregiverHomeDemoNotifElder);
        btnCaregiverHomeDemoNotifElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_caregiverHomeFragment_to_notifikasidariElderFragment);
            }
        });

        // Logout
        Button btnCaregiverHomeLogout = view.findViewById(R.id.btnCaregiverHomeLogout);
        btnCaregiverHomeLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}