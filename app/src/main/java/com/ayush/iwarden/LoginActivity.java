package com.ayush.iwarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    Button stud;
    View card;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        card = findViewById(R.id.card_vw);

        if(savedInstanceState==null) {
            LogAsFragment logAsFragment = new LogAsFragment(this);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.card_vw, logAsFragment, null)
                    .commit();
//            stud = card.findViewById(R.id.stud_butn);
//            stud.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LogAsStudent();
//                }
//            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void LogAsStudent(String name){
        LogInFragment logInFragment = new LogInFragment(name,this);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.card_vw, logInFragment, null)
                .commit();
    }

    public void SucessfulLogin(){
        Intent it = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(it);
    }
}