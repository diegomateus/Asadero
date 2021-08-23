package com.example.elpaisanomateus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button nextButton;
    Button waitersButton;
    EditText tableNumberEditText;
    Spinner waitersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        String meseros[] = {"Liz","Valentina","Isabella","Andres","Cristian","Henry"};

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), tableNumberEditText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        tableNumberEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_waiter,meseros);
        waitersSpinner.setAdapter(adapter);
    }


}