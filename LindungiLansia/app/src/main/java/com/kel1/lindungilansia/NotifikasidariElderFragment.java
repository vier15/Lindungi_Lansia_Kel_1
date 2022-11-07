package com.kel1.lindungilansia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifikasidariElderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifikasidariElderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotifikasidariElderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifikasidariElderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifikasidariElderFragment newInstance(String param1, String param2) {
        NotifikasidariElderFragment fragment = new NotifikasidariElderFragment();
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
        View view = inflater.inflate(R.layout.fragment_notifikasidari_elder, container, false);

        // Navigasi ke halaman bisa bantu elder
        Button btnBantuNotifikasiDariElder = view.findViewById(R.id.btnBantuNotifikasiDariElder);
        btnBantuNotifikasiDariElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_notifikasidariElderFragment_to_caregiverBisaBantuElderFragment);
            }
        });

        // Navigasi ke halaman tidak bisa bantu elder
        Button btnTidakBisaBantuNotifikasiDariElder = view.findViewById(R.id.btnTidakBisaBantuNotifikasiDariElder);
        btnTidakBisaBantuNotifikasiDariElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_notifikasidariElderFragment_to_caregiverTidakBisaBantuElderFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Countdown timer untuk notifikasi dari elder
        TextView tvCountdownNotifikasiDariElder = view.findViewById(R.id.tvCaregiverMelihatJadwalObatElderNamaElder);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountdownNotifikasiDariElder.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                // Jika countdown selesai, pindah ke halaman caregiver tidak bisa bantu elder
                Navigation.findNavController(view).navigate(R.id.action_notifikasidariElderFragment_to_caregiverTidakBisaBantuElderFragment);
            }
        }.start();
    }
}