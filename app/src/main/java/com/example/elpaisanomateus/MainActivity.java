package com.example.elpaisanomateus;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bussiness.Dish;
import bussiness.Waiter;

public class MainActivity extends AppCompatActivity {

    public static final String PATH_CHOPPED="kitchen/";
    public static final String PATH_MIXED="beef-chigüiro";
    public static final String PATH_25 = "25/";
    public static final String PATH_30 = "30/";
    public static final String PATH_35 = "35/";
    public static final String PATH_40 = "40/";

    private FirebaseDatabase database;
    public DatabaseReference myRef;
    Button nextButton;
    Button waitersButton;
    EditText tableNumberEditText;
    Spinner waitersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        nextButton = findViewById(R.id.nextButton);
        waitersButton = findViewById(R.id.waitersButton);
        tableNumberEditText = findViewById(R.id.tableNumberEditText);
        waitersSpinner = findViewById(R.id.waitersSpinner);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String meseros[] = {"Liz","Milena","Isabella","Andres","Cristian","Henry"};

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dish nuevo = new Dish();
                nuevo.setName("PICADA_25_RES_CHIGÜIRO");
                nuevo.setPrice(25000L);
                myRef = database.getReference(PATH_CHOPPED + PATH_25 + PATH_MIXED);
                myRef.setValue(nuevo);

                Dish nuevo2 = new Dish();
                nuevo2.setName("PICADA_30_RES_CHIGÜIRO");
                nuevo2.setPrice(30000L);
                myRef = database.getReference(PATH_CHOPPED + PATH_30 + PATH_MIXED);
                myRef.setValue(nuevo2);

                Dish nuevo3 = new Dish();
                nuevo3.setName("PICADA_35_RES_CHIGÜIRO");
                nuevo3.setPrice(35000L);
                myRef = database.getReference(PATH_CHOPPED + PATH_35 + PATH_MIXED);
                myRef.setValue(nuevo3);

                Dish nuevo4 = new Dish();
                nuevo4.setName("PICADA_40_RES_CHIGÜIRO");
                nuevo4.setPrice(40000L);
                myRef = database.getReference(PATH_CHOPPED + PATH_40 + PATH_MIXED);
                myRef.setValue(nuevo4);

            }
        });

        tableNumberEditText.setOnClickListener(v -> {

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_waiter, meseros);
        waitersSpinner.setAdapter(adapter);
    }


}