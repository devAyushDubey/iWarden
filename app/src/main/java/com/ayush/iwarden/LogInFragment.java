package com.ayush.iwarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tx;
    private String st;
    FirebaseAuth mAuth;
    LoginActivity mlogin;

    public LogInFragment() {
        // Required empty public constructor
    }

    public LogInFragment(String name,LoginActivity login) {
        st = name;
        mlogin = login;
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
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
        mAuth = FirebaseAuth.getInstance();
        View inflated = inflater.inflate(R.layout.fragment_log_in, container, false);
        ProgressBar prg = (ProgressBar)inflated.findViewById(R.id.progressBar);
        prg.setVisibility(View.GONE);

        tx = inflated.findViewById(R.id.login);
        tx.setText(st + " Login");

        EditText email_et = inflated.findViewById(R.id.editTextTextEmailAddress);
        EditText pass_et = inflated.findViewById(R.id.editTextTextPassword);
        Button login = inflated.findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_et.getText().toString();
                String pass = pass_et.getText().toString();
                //Toast.makeText(mlogin, email + pass, Toast.LENGTH_SHORT).show();
                prg.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prg.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            mlogin.SucessfulLogin();
                        }
                        else {
                            Toast.makeText(mlogin, "Sign-in unsucessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return inflated;
    }
}