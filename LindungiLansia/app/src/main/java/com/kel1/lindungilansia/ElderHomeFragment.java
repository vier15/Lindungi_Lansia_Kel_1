package com.kel1.lindungilansia;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ElderHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ElderHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ElderHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ElderHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ElderHomeFragment newInstance(String param1, String param2) {
        ElderHomeFragment fragment = new ElderHomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_elder_home, container, false);

        DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawerLayout);

        // Navigasi ke navbar
        FloatingActionButton btnNavBar = view.findViewById(R.id.btnNavBar);
        btnNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Navigasi ke navbar
//        FloatingActionButton btnNavBar = view.findViewById(R.id.btnNavBar);
//        btnNavBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.action_elderHomeFragment_to_navBarFragment);
//            }
//        });

        // Navigasi butuh bantuan
        Button btnElderHomeButuhBantuan = view.findViewById(R.id.btnElderHomeButuhBantuan);
        btnElderHomeButuhBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_elderHomeFragment_to_meminta_BantuanFragment);
            }
        });

        Button Button_maps_demo = view.findViewById(R.id.Button_maps_demo);
        Button_maps_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_elderHomeFragment_to_elderMapsPositionFragment);
            }
        });

        Button btnKeProfile = view.findViewById(R.id.btnKeProfile);
        btnKeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_elderHomeFragment_to_navBarFragment);
            }
        });
//        NavigationView navigationView = view.findViewById(R.id.nvElderHomeSidebar);
////        navigationView.setItemIconTintList(null);
////
////        NavController navController = Navigation.findNavController(MainActivity, R.id.fragmentContainerView);
////        NavigationUI.setupWithNavController(navigationView, navController);

        return view;
    }
}