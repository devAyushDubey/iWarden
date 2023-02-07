package com.ayush.iwarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StuFormActivity extends AppCompatActivity {

    Button add;
    EditText name;
    EditText id;
    EditText room;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_form);

        firebaseFirestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.editTextName);
        id = findViewById(R.id.editTextRoll);
        room = findViewById(R.id.editTextRoom);
        add = findViewById(R.id.button_add);

        Map mp = new HashMap<>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText()!=null && id.getText()!=null && room.getText()!=null){
                    mp.put("NAME",name.getText().toString());
                    mp.put("ID", Long.parseLong(id.getText().toString()));
                    mp.put("ROOM", Integer.parseInt(room.getText().toString()));

                    firebaseFirestore.collection("residents").document(id.getText().toString()).set(mp).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(StuFormActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(StuFormActivity.this, "Error Adding Data" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}