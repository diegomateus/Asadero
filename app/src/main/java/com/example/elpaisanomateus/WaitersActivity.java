package com.example.elpaisanomateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;
import adapters.WaitersAdapter;
import bussiness.Waiter;

public class WaitersActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView waitersRecyclerView;
    private WaitersAdapter waitersAdapter;
    private ArrayList <Waiter> waiters;
    private ArrayList <String> waitersName;
    private Button addWaiterButton;
    private DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiters);

        dividerItemDecoration = new DividerItemDecoration(this,1);
        waiters = new ArrayList<>();
        waitersName = new ArrayList<>();
        waitersRecyclerView = findViewById(R.id.waitersRecyclerView);
        waitersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference();
        addWaiterButton = findViewById(R.id.addWaiterButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        waiters.clear();
        getWaitersFromFirebase();
        addWaiterButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),AddWaiterActivity.class);

            for (Waiter waiter: waiters) {
                waitersName.add(waiter.getName());
            }

            intent.putExtra("waiters", waitersName);
            startActivity(intent);
        });
    }

    public void getWaitersFromFirebase(){
        databaseReference.child("waiters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    String name = Objects.requireNonNull(singleSnapshot.child("name").getValue()).toString();
                    Waiter aux = new Waiter();
                    aux.setName(name);
                    waiters.add(aux);
                }

                waitersAdapter = new WaitersAdapter(waiters, R.layout.adapter_waiter);
                waitersRecyclerView.setAdapter(waitersAdapter);
                waitersRecyclerView.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}