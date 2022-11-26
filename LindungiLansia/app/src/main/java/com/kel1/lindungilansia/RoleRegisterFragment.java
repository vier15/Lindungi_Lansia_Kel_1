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
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoleRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoleRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DbUser dbuser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RoleRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoleRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoleRegisterFragment newInstance(String param1, String param2) {
        RoleRegisterFragment fragment = new RoleRegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_role_register, container, false);

        // Membuka database
        dbuser = new DbUser(getActivity().getApplicationContext());
        dbuser.open();

        // Membuka shared preferences
        SharedPreferences sp = getActivity().getSharedPreferences("com.kel1.lindungilansia.sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Navigasi ke halaman elder home
        ImageButton imgBtnElderRegister = view.findViewById(R.id.imgBtnElderRegister);
        imgBtnElderRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update role akun yang baru mendaftar menjadi Elder
                int id = sp.getInt("id", 0);
                String email = sp.getString("email", "");
                dbuser.updateRoleUser("Elder", id, email);

                // Simpan role user di shared preferences
                editor.putString("role", "Elder");

                Navigation.findNavController(view).navigate(R.id.action_roleRegisterFragment_to_elderHomeFragment);
            }
        });

        // Navigasi ke halaman caregiver home
        ImageButton imgBtnCaregiverRegister = view.findViewById(R.id.imgBtnCaregiverRegister);
        imgBtnCaregiverRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update role akun yang baru mendaftar menjadi Caregiver
                int id = sp.getInt("id", 0);
                String email = sp.getString("email", "");
                dbuser.updateRoleUser("Caregiver", id, email);

                // Simpan role user di shared preferences
                editor.putString("role", "Caregiver");

                Navigation.findNavController(view).navigate(R.id.action_roleRegisterFragment_to_caregiverHomeFragment);
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