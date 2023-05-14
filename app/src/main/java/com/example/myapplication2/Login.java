package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername,textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignup,textViewHome;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password_login);
        buttonLogin =findViewById(R.id.btnlogin);
        textViewSignup = findViewById(R.id.signUpText);
        textViewHome=findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progresslog);
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname,username,password,email;
                username= String.valueOf(textInputEditTextUsername.getText());
                password= String.valueOf(textInputEditTextPassword.getText());

        if(!username.equals("")&&!password.equals("")) {
            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;

                    PutData putData = new PutData("http://192.168.0.104/loginApp/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if(result.equals("Login Success")){
                                Intent intent= new Intent(getApplicationContext(),calculate.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "All fields required ",Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}