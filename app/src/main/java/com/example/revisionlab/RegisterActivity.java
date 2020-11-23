package com.example.revisionlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.revisionlab.Database.DBHandler;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText username, password;
    CheckBox student, teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btnRegister2);
        username = findViewById(R.id.etUsernameReg);
        password = findViewById(R.id.etPasswordReg);
        student = findViewById(R.id.checkStudent);
        teacher = findViewById(R.id.checkTeacher);

        //register button onclick
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(RegisterActivity.this);

                if(student.isChecked()){
                    long val = dbHandler.addUser(username.getText().toString(), password.getText().toString(), "Student");

                    if (val> 0) {
                        Toast.makeText(RegisterActivity.this, "Registered Student Successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                else if(teacher.isChecked()){
                    long val = dbHandler.addUser(username.getText().toString(), password.getText().toString(), "Teacher");

                    if(val> 0) {
                        Toast.makeText(RegisterActivity.this, "Registered Teacher Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }
}