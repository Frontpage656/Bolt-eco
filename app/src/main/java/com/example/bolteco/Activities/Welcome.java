package com.example.bolteco.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.bolteco.R;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    Button login;
    TextView sign_up;
    FirebaseAuth aut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        aut = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);
        sign_up = findViewById(R.id.sign_up);

        login.setOnClickListener(this);
        sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Intent intent = new Intent(Welcome.this, Login_ac.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sign_up:
                Intent intent2 = new Intent(Welcome.this, Register_ac.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (aut.getCurrentUser() != null){
            startActivity(new Intent(Welcome.this, Home_page.class));
        }
    }
}