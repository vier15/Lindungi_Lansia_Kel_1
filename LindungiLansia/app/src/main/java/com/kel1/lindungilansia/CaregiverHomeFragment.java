package com.kel1.lindungilansia;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaregiverHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaregiverHomeFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_caregiver_home, container, false);

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

        return view;
    }
}