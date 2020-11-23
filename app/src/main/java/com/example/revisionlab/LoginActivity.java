package com.example.revisionlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.revisionlab.Database.DBHandler;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister, btnLogin;
    EditText userName, passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        userName = findViewById(R.id.etUsernameLogin);
        passWord = findViewById(R.id.etPasswordLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(LoginActivity.this);

                if(dbHandler.validateUser(userName.getText().toString().trim(), passWord.getText().toString().trim()) == 1){
                    Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                    intent.putExtra("userNameTeacher", userName.getText().toString());
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }

                else if(dbHandler.validateUser(userName.getText().toString().trim(), passWord.getText().toString().trim()) == 2){
                    Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                    intent.putExtra("usernameStudent", userName.getText().toString());
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(LoginActivity.this, "Invalid Login or User doesn't Exist", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}