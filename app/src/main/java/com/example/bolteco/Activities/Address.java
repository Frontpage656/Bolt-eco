package com.example.bolteco.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolteco.Adapt.RecyclerAdatpterAddress;
import com.example.bolteco.ModeClass.Address_mode_class;
import com.example.bolteco.ModeClass.Feature_mode_class;
import com.example.bolteco.ModeClass.OnTrend_mode_class;
import com.example.bolteco.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Address extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;


    RecyclerView recycle_address;
    RecyclerView.LayoutManager linearLayout;
    RecyclerAdatpterAddress adatpterAddress;
    List<Address_mode_class> list = new ArrayList<>();

    TextView add_address_btn, pay_btn;
    ImageView back_arrow;

    Feature_mode_class featureModeClass;
    OnTrend_mode_class onTrendModeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        Object object = getIntent().getSerializableExtra("items");

        back_arrow = findViewById(R.id.back_arrow);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        add_address_btn = findViewById(R.id.add_address_btn);
        pay_btn = findViewById(R.id.pay_btn);

        recycle_address = findViewById(R.id.recycle_address);

        recycle_address.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(Address.this);
        recycle_address.setLayoutManager(linearLayout);

        adatpterAddress = new RecyclerAdatpterAddress(Address.this, list);
        recycle_address.setAdapter(adatpterAddress);

        firestore.collection("User").document(auth.getCurrentUser().getUid())
                .collection("Address")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "Error occured " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (DocumentChange documentChange : value.getDocumentChanges()) {

                            if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                list.add(documentChange.getDocument().toObject(Address_mode_class.class));
                            }
                            adatpterAddress.notifyDataSetChanged();

                            if(list.size()== 0){
                                pay_btn.setVisibility(View.GONE);
                            }
                        }

                    }
                });


        add_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Address.this, AddAddress.class));
            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double amount = 0.0;
                if (object instanceof Feature_mode_class) {

                    featureModeClass = (Feature_mode_class) object;
                    amount = featureModeClass.getPrice();
                }
                if (object instanceof OnTrend_mode_class) {

                    onTrendModeClass = (OnTrend_mode_class) object;
                    amount = onTrendModeClass.getPrice();
                }

                Intent intent = new Intent(Address.this, Payments.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Address.this, ui_details.class));
                finish();
            }
        });

    }
}