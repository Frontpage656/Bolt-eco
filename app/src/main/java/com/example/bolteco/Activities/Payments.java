package com.example.bolteco.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolteco.ModeClass.Feature_mode_class;
import com.example.bolteco.ModeClass.OnTrend_mode_class;
import com.example.bolteco.R;
import com.google.androidbrowserhelper.playbilling.provider.PaymentActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Payments extends AppCompatActivity implements PaymentResultListener {
    ImageView back_arrow;
    TextView subTotal,shipping,total,pay_btn;


    double shippingValue = 5.0;
    double totalValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        Checkout.preload(getApplicationContext());

        back_arrow = findViewById(R.id.back_arrow);
        subTotal = findViewById(R.id.subTotal);
        shipping = findViewById(R.id.shipping);
        total = findViewById(R.id.total);
        pay_btn =findViewById(R.id.pay_btn);


       double amount = getIntent().getDoubleExtra("amount",0.0);
        subTotal.setText(amount+"$");
        shipping.setText(shippingValue+"$");

        totalValue = amount + shippingValue;
        total.setText(totalValue+"$");
        

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payments.this, Address.class));
                finish();
            }
        });
        
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

    }

    public void startPayment() {
        Toast.makeText(Payments.this, "Checkout btn", Toast.LENGTH_LONG).show();

        Checkout checkout = new Checkout();

        final Activity activity = Payments.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "TechTibet");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");

            // Currency type
            options.put("currency", "USD");

            //multiply with 100 to get exact amount in Tanzania shillings
            totalValue = totalValue * 2300;
            //amount
            options.put("amount", totalValue);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "joshuasimon656@gmail.com");
            //contact
            preFill.put("contact", "745051250");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(), "Payments Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), "Payments failed", Toast.LENGTH_SHORT).show();
    }
}