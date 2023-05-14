package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class SignUp extends AppCompatActivity {

TextInputEditText textInputEditTextFullname,textInputEditTextUsername,textInputEditTextPassword,textInputEditTextEmail;
Button buttoSignup;
TextView textViewLogin,textViewHome;
ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputEditTextUsername=findViewById(R.id.username);
        textInputEditTextFullname=findViewById(R.id.fullname);
        textInputEditTextPassword=findViewById(R.id.password);
        textInputEditTextEmail=findViewById(R.id.email);
        buttoSignup=findViewById(R.id.buttonSignUp);
        textViewLogin=findViewById(R.id.loginText2);
        textViewHome=findViewById(R.id.loginText4);
        progressBar=findViewById(R.id.progress);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
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
        buttoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname,username,password,email;
                fullname= String.valueOf(textInputEditTextFullname.getText());
                username= String.valueOf(textInputEditTextUsername.getText());
                password= String.valueOf(textInputEditTextPassword.getText());
                email= String.valueOf(textInputEditTextEmail.getText());

                if(!fullname.equals("")&&!username.equals("")&&!password.equals("")&&!email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;

                            PutData putData = new PutData("http://192.168.0.104/loginApp/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Intent intent= new Intent(getApplicationContext(),Login.class);
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