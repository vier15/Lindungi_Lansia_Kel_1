package com.kel1.lindungilansia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bantuan_BatalFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Bantuan_BatalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bantuan_BatalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bantuan_BatalFragment newInstance(String param1, String param2) {
        Bantuan_BatalFragment fragment = new Bantuan_BatalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Bantuan_BatalFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bantuan__batal, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                Navigation.findNavController(view).navigate(R.id.action_bantuan_BatalFragment_to_elderHomeFragment);
            }
        }.start();
    }


}