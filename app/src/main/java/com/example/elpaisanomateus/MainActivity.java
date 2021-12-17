package com.example.elpaisanomateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import bussiness.Waiter;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private Button foodsButton;
    private Button waitersButton;
    private List<Waiter> waitersName;
    private EditText tableNumberEditText;
    public Spinner waitersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waitersButton = findViewById(R.id.waitersButton);
        foodsButton = findViewById(R.id.foodsButton);
        waitersSpinner = findViewById(R.id.waitersSpinner);
        tableNumberEditText = findViewById(R.id.tableNumberEditText);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        waitersName = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        foodsButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(),WaitersActivity.class)));
        waitersButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(),WaitersActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        waitersName.clear();
        getWaitersNameFromFirebase();

    }

    private void getWaitersNameFromFirebase(){

        databaseReference.child("waiters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    String name = Objects.requireNonNull(singleSnapshot.child("name").getValue()).toString();
                    waitersName.add(new Waiter(name,0L));
                }

                waitersSpinner.setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item_waiter, waitersName));
                waitersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}