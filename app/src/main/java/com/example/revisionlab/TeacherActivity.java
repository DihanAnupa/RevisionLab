package com.example.revisionlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.revisionlab.Database.DBHandler;

public class TeacherActivity extends AppCompatActivity {
    EditText subject, message;
    TextView tUsername;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        tUsername = findViewById(R.id.txtViewTeacher);
        subject = findViewById(R.id.etSubjectT);
        message = findViewById(R.id.etMessageT);
        send = findViewById(R.id.btnSendT);

        //obtain username from previous activity
        Intent intent = getIntent();
        String userNameT = intent.getStringExtra("usernameTeacher");
        tUsername.setText(userNameT);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(TeacherActivity.this);

                long val = dbHandler.addMessage(userNameT, subject.getText().toString().trim(), message.getText().toString().trim());

                if(val > 0){
                    Toast.makeText(TeacherActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TeacherActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}