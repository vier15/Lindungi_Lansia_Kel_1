package com.kel1.lindungilansia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaregiverLihatProfilElderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaregiverLihatProfilElderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaregiverLihatProfilElderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaregiverLihatProfilElderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaregiverLihatProfilElderFragment newInstance(String param1, String param2) {
        CaregiverLihatProfilElderFragment fragment = new CaregiverLihatProfilElderFragment();
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
        View view = inflater.inflate(R.layout.fragment_caregiver_lihat_profil_elder, container, false);

        // Navigasi ke halaman caregiver home
        Button btnKembaliCaregiverLihatProfilElder = view.findViewById(R.id.btnKembaliCaregiverLihatProfilElder);
        btnKembaliCaregiverLihatProfilElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_caregiverLihatProfilElderFragment_to_caregiverHomeFragment);
            }
        });

        return view;
    }
}