package com.example.bolteco.Activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolteco.Adapt.RecyclerFeatureAdapter;
import com.example.bolteco.Adapt.RecyclerOnTrendAdapter;
import com.example.bolteco.Adapt.RecyclerViewAdapter;
import com.example.bolteco.ModeClass.Category_mode_class;
import com.example.bolteco.ModeClass.Feature_mode_class;
import com.example.bolteco.ModeClass.OnTrend_mode_class;
import com.example.bolteco.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    RecyclerView recycle_view_category,recycle_view_features,recycle_view_onTrend;
    RecyclerView.LayoutManager linearLayout,feature_layout,onTrend_layout;


    RecyclerViewAdapter categoryAdapter;
    RecyclerFeatureAdapter featureAdapter;
    RecyclerOnTrendAdapter onTrendAdapter;

    List<Category_mode_class> modeClass_category = new ArrayList<>();
    List<Feature_mode_class> modeClass_feature = new ArrayList<>();
    List<OnTrend_mode_class> modeClass_onTrend = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_fragments);


        recycle_view_category = findViewById(R.id.recycle_view_category);
        recycle_view_features = findViewById(R.id.recycle_view_features);
        recycle_view_onTrend = findViewById(R.id.recycle_view_onTrend);

        linearLayout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        feature_layout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        onTrend_layout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        //For Category
        recycle_view_category.setHasFixedSize(true);
        recycle_view_category.setLayoutManager(linearLayout);
        categoryAdapter = new RecyclerViewAdapter(Home_page.this, modeClass_category);
        recycle_view_category.setAdapter(categoryAdapter);


        //For Features
        recycle_view_features.setHasFixedSize(true);
        featureAdapter = new RecyclerFeatureAdapter(Home_page.this,modeClass_feature);
        recycle_view_features.setLayoutManager(feature_layout);
        recycle_view_features.setAdapter(featureAdapter);


        //For OnTrend
        recycle_view_onTrend.setHasFixedSize(true);
        onTrendAdapter = new RecyclerOnTrendAdapter(Home_page.this,modeClass_onTrend);
        recycle_view_onTrend.setLayoutManager(onTrend_layout);
        recycle_view_onTrend.setAdapter(onTrendAdapter);


        db.collection("Category").orderBy("category_name",Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            Toast.makeText(getApplicationContext(), "Error occured "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                            for (DocumentChange documentChange : value.getDocumentChanges()) {

                                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                    modeClass_category.add(documentChange.getDocument().toObject(Category_mode_class.class));
                                }
                                categoryAdapter.notifyDataSetChanged();
                        }

                    }
                });
        db.collection("Features").orderBy("productName",Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            Toast.makeText(getApplicationContext(), "Error occured "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (DocumentChange documentChange : value.getDocumentChanges()) {

                            if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                modeClass_feature.add(documentChange.getDocument().toObject(Feature_mode_class.class));
                            }
                            featureAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("Ontrend").orderBy("num_buyers",Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            Toast.makeText(getApplicationContext(), "Error occured "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (DocumentChange documentChange : value.getDocumentChanges()) {

                            if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                modeClass_onTrend.add(documentChange.getDocument().toObject(OnTrend_mode_class.class));
                            }
                            onTrendAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}