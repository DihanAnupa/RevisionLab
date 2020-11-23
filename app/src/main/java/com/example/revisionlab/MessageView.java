package com.example.revisionlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageView extends AppCompatActivity {
    TextView subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        subject = findViewById(R.id.txtViewSubject);
        message = findViewById(R.id.txtViewMessage);

        Intent intent = getIntent();

        subject.setText(intent.getStringExtra("subject"));
        message.setText(intent.getStringExtra("message"));

    }
}