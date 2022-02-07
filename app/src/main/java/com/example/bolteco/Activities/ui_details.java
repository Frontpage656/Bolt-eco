package com.example.bolteco.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bolteco.ModeClass.Feature_mode_class;
import com.example.bolteco.ModeClass.OnTrend_mode_class;
import com.example.bolteco.R;

public class ui_details extends AppCompatActivity implements View.OnClickListener {
    ImageView back_arrow;
    TextView product_name,product_price,likes_count,product_description,add_to_cart_btn,buy_button;
    ImageView product_image,like_btn;

    Feature_mode_class featureModeClass = null;
    OnTrend_mode_class onTrendModeClass = null;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ui_details);

        back_arrow = findViewById(R.id.back_arrow);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        likes_count = findViewById(R.id.likes_count);
        product_description = findViewById(R.id.product_description);
        add_to_cart_btn = findViewById(R.id.add_to_cart_btn);
        buy_button = findViewById(R.id.buy_button);
        product_image = findViewById(R.id.product_image);
        like_btn = findViewById(R.id.like_btn);

        back_arrow.setOnClickListener(this);
        buy_button.setOnClickListener(this);
        add_to_cart_btn.setOnClickListener(this);
        like_btn.setOnClickListener(this);

        Object object = getIntent().getSerializableExtra("details");

        // Shit is here........
        if(object instanceof Feature_mode_class){
            featureModeClass = (Feature_mode_class) object;
        }else{
            onTrendModeClass = (OnTrend_mode_class) object;
        }
        if (featureModeClass!=null){

            product_name.setText(featureModeClass.getProductName());
            product_price.setText(featureModeClass.getPrice()+"$");
            product_description.setText(featureModeClass.getDescription());

            Glide.with(getApplicationContext()).load(featureModeClass.getProductImage()).into(product_image);
        }
        if (onTrendModeClass != null){
            product_name.setText(onTrendModeClass.getName());
            product_price.setText(onTrendModeClass.getPrice()+"$");
            product_description.setText(onTrendModeClass.getDescription());

            Glide.with(getApplicationContext()).load(onTrendModeClass.getImage_url()).into(product_image);
        }

    }
 
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_to_cart_btn:
                // do here
                Toast.makeText(getApplicationContext(), "cart button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_arrow:
                startActivity(new Intent(ui_details.this,Home_page.class));
                finish();
                break;
            case R.id.like_btn:
                // Do here
                Toast.makeText(getApplicationContext(), "Like button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy_button:

                Intent intent = new Intent(ui_details.this, Address.class);

                if (featureModeClass != null){
                    intent.putExtra("items",featureModeClass);
                }
                if(onTrendModeClass != null){
                    intent.putExtra("items", onTrendModeClass);
                }
                startActivity(intent);
                break;
        }
    }
}