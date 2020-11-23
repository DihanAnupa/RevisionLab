package com.example.revisionlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.revisionlab.Database.DBHandler;
import com.example.revisionlab.Database.Message;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private static final String TAG = "StudentActivity";
    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private DBHandler dbHandler = new DBHandler(StudentActivity.this);
    private RecyclerAdapter recyclerAdapter;

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        username = findViewById(R.id.textViewStudentUsername);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("usernameStudent"));
        initViews();
        initObjects();
    }

    private void initViews(){
        recyclerView = findViewById(R.id.messageRecycler);
    }
    private void initObjects(){
        messages.addAll(dbHandler.getAllmsgs());
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }
}