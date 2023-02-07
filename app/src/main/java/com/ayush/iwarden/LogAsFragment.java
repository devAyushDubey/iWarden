package com.ayush.iwarden;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogAsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogAsFragment extends Fragment {

    LoginActivity mlogin;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogAsFragment(){
    }

    public LogAsFragment(LoginActivity login) {
        mlogin = login;
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogAsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogAsFragment newInstance(String param1, String param2) {
        LogAsFragment fragment = new LogAsFragment();
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
        View inflated = inflater.inflate(R.layout.fragment_log_as, container, false);
        Button stud = inflated.findViewById(R.id.stud_butn);
        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlogin.LogAsStudent(stud.getText().toString());
            }
        });
        Button admin = inflated.findViewById(R.id.admin_butn);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlogin.LogAsStudent(admin.getText().toString());
            }
        });
        Button staff = inflated.findViewById(R.id.staff_butn);
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlogin.SucessfulLogin();
//                mlogin.LogAsStudent(staff.getText().toString());
            }
        });
        // Inflate the layout for this fragment
        return inflated;
    }
}