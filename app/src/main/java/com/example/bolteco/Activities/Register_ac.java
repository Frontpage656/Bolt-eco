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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_ac extends AppCompatActivity implements View.OnClickListener {
    private TextView login_btn;
    private ImageView back_arrow;
    EditText userName, newUser_email, newUser_password, password_comfim;
    private Button signup_btn;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userName);
        newUser_email = findViewById(R.id.newUser_email);
        newUser_password = findViewById(R.id.newUser_password);
        password_comfim = findViewById(R.id.password_comfim);

        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(this);

        back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(this);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                Intent intent = new Intent(Register_ac.this, Login_ac.class);
                startActivity(intent);
                finish();
                break;
            case R.id.back_arrow:
                Intent intent1 = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.signup_btn:
                signUpNow();
                break;
        }
    }

    private void signUpNow() {

        if (!newUser_email.getText().toString().equals("")) {
            if (!userName.getText().toString().equals("")) {
                if (!newUser_password.getText().toString().equals("") && !password_comfim.getText().toString().equals("")) {
                    if (newUser_password.getText().toString().equals(password_comfim.getText().toString())) {
                        auth.createUserWithEmailAndPassword(newUser_email.getText().toString().trim(), newUser_password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();
                                            Intent allAc = new Intent(Register_ac.this, Home_page.class);
                                            startActivity(allAc);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Fail to add user", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(getApplicationContext(), "Password are not equal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
        }

    }

}