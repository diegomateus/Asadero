package com.example.elpaisanomateus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Locale;
import bussiness.Waiter;

public class AddWaiterActivity extends AppCompatActivity {

    public static final String PATH_WAITERS = "waiters/";

    private EditText editTextName;
    //private ImageView imageViewWaiter;
    public ImageButton imageButtonCamera;
    public ImageButton imageButtonGallery;
    public Button buttonAdd;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public Waiter waiter;
    public String name;
    public String nameModifier;
    public ArrayList <String> waiters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_waiter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        waiters = new ArrayList<>();

        editTextName = findViewById(R.id.nameEditText);
        //imageViewWaiter = findViewById(R.id.imageViewWaiter);
        imageButtonCamera = findViewById(R.id.imageButtonCamera);
        imageButtonGallery = findViewById(R.id.imageButtonGallery);
        buttonAdd = findViewById(R.id.addWaiterButton);

        waiters = (ArrayList<String>) getIntent().getSerializableExtra("waiters");

    }

    @Override
    protected void onResume() {
        super.onResume();

        buttonAdd.setOnClickListener(v -> {

            name = editTextName.getText().toString();
            nameModifier = name.toLowerCase(Locale.ROOT);

            if((editTextName.getText().toString().isEmpty())){
                Toast.makeText(v.getContext(), "Ingrese un nombre", Toast.LENGTH_LONG).show();
            }

            else if (editTextName.getText().toString().length() < 3){
                Toast.makeText(v.getContext(), "Ingrese un nombre con más de 3 caracteres", Toast.LENGTH_LONG).show();
            }

            else if (validateWaiter(nameModifier)){
                Toast.makeText(v.getContext(), "Ese mesero ya esta inscrito", Toast.LENGTH_LONG).show();
            }
            else {

                databaseReference = firebaseDatabase.getReference(PATH_WAITERS + nameModifier);
                waiter = new Waiter();
                nameModifier = ucFirst(nameModifier);
                waiter.setName(nameModifier);
                waiter.setSells(0);
                databaseReference.setValue(waiter);
                startActivity(new Intent(v.getContext(),WaitersActivity.class));
            }
        });
    }

    public String ucFirst(String name){
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public boolean validateWaiter(String name){
        String nameModifier = ucFirst(name);
        for (int i = 0; i < waiters.size(); i++){
            if (nameModifier.equals(waiters.get(i))){
                return true;
            }
        }
        return false;
    }

}