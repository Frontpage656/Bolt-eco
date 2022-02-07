package com.example.bolteco.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bolteco.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_ac extends AppCompatActivity implements View.OnClickListener {
    ImageView back_arrow;
    TextView sign_btn;
    Button login_btn;
    EditText user_email, user_password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        user_password = findViewById(R.id.user_password);
        user_email = findViewById(R.id.user_email);
        login_btn = findViewById(R.id.login_btn);

        back_arrow = findViewById(R.id.back_arrow);
        sign_btn = findViewById(R.id.sign_btn);

        sign_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_arrow:
                Intent intent = new Intent(Login_ac.this, Welcome.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sign_btn:
                Intent intent2 = new Intent(Login_ac.this, Register_ac.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.login_btn:
                login();
        }
    }

    private void login() {
        if (!user_email.getText().toString().isEmpty() && !user_password.getText().toString().isEmpty()) {

            auth.signInWithEmailAndPassword(user_email.getText().toString(), user_password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Nice login!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login_ac.this, Home_page.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Fields cant be empty", Toast.LENGTH_SHORT).show();
        }
    }
}