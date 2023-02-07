package com.ayush.iwarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


//    public MainActivity(){
//        super(R.layout.activity_main);
//    }


    Fragment home;
    Fragment studet;
    Fragment inbox;
    Fragment settings;

    BottomNavigationView bottomNavigationView;
    FloatingActionButton flt;

    FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new DashFragment(this);
        studet = new StuDetFragment();
        inbox = new InboxFragment();
        settings = new SettingsFragment();


        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.Frag_container,home,null).commit();


        bottomNavigationView = findViewById(R.id.nav_view);
        flt = findViewById(R.id.floatingActionButton);
        flt.setVisibility(View.GONE);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        flt.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.Frag_container,home,null).commit();
                        break;
                    case R.id.nav_student:
                        flt.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.Frag_container,studet,null).commit();
                        break;
                    case R.id.nav_inbox:
                        flt.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.Frag_container,inbox,null).commit();
                        break;
                    case R.id.nav_settings:
                        flt.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.Frag_container,settings,null).commit();
                        break;
                }
                return true;
            }
        });


        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StuFormActivity.class));
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser == null){
                    Intent it = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(it);
                }else{

                }
            }
        };
    }
}