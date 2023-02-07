package com.ayush.iwarden;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StuDetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StuDetFragment extends Fragment {

    Context mcontext;

    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;

    ArrayList<StudentResult> results;
    ResultAdapter resultAdapter;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StuDetFragment() {
        // Required empty public constructor
    }

    public StuDetFragment(Context context) {
        mcontext = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StuDetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StuDetFragment newInstance(String param1, String param2) {
        StuDetFragment fragment = new StuDetFragment();
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

        if(savedInstanceState == null){
            results = new ArrayList<>();
        }

        firebaseFirestore = FirebaseFirestore.getInstance();


        firebaseFirestore.collection("residents").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Log.v(this.getClass().toString(),document.getId() + "=>" + document.getData());
                        results.add(new StudentResult(document.getLong("ID"),document.getString("NAME"),document.getLong("ROOM")));
                    }
                    resultAdapter = new ResultAdapter(results);
                    recyclerView.setAdapter(resultAdapter);
                }
                else {
                    Log.v(this.getClass().toString(),"Error fetching data: " + task.getException());
                }
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stu_det, container, false);

        recyclerView = view.findViewById(R.id.result_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Drawable butn = getResources().getDrawable(R.drawable.round_corner);
        Button btn = view.findViewById(R.id.button2);
        EditText search = (EditText)view.findViewById(R.id.search);
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search2,0,0,0);
                    butn.setTint(Color.parseColor("#27C8D0"));
                    btn.setBackground(butn);
                }
                else{
                    search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search,0,0,0);
                    butn.setTintList(null);
                }
            }
        });


        return view;
    }
}