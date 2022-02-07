package com.example.bolteco.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolteco.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddAddress extends AppCompatActivity {
    EditText input_name,input_address_lane,input_city,input_post_code,input_phone;
    TextView add_btn;
    LinearLayout for_snack;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        input_name = findViewById(R.id.input_name);
        input_address_lane = findViewById(R.id.input_address_lane);
        input_city = findViewById(R.id.input_city);
        input_post_code = findViewById(R.id.input_post_code);
        input_phone = findViewById(R.id.input_phone);
        add_btn = findViewById(R.id.add_btn);
        for_snack = findViewById(R.id.for_snack);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String finalAddress = "";
                if(!input_name.toString().isEmpty()){
                    finalAddress+=input_name.getText().toString()+", ";
                }
                if(!input_address_lane.toString().isEmpty()){
                    finalAddress+=input_address_lane.getText().toString()+", ";
                }
                if(!input_city.toString().isEmpty()){
                    finalAddress+=input_city.getText().toString()+", ";
                }
                if(!input_post_code.toString().isEmpty()){
                    finalAddress+=input_post_code.getText().toString()+", ";
                }
                if(!input_phone.toString().isEmpty()){
                    finalAddress+=input_phone.getText().toString()+", ";
                }


                Map<String,String> map = new HashMap<>();
                map.put("address",finalAddress);

                firestore.collection("User").document((auth.getCurrentUser()).getUid())
                        .collection("Address")
                        .add(map)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(getApplicationContext(), "Address saved", Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                            }
                        });
            }
        });

    }
}