package com.example.audiochat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText textInput;
    private TextView textChattingWith;
    private Button button_mic;
    private ImageView button_send;
    private ArrayList<Message> messages;
    String usernameOfRoomy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_message);
        recyclerView = findViewById(R.id.recyclerChat);
        textInput = findViewById(R.id.textChat);
        textChattingWith = findViewById(R.id.chattingPerson);
        //button_mic = findViewById(R.id.buttonRecord);
        button_send = findViewById(R.id.imageView);
        messages = new ArrayList<>();
        usernameOfRoomy = getIntent().getStringExtra("usernames_of_room");

        textChattingWith.setText(usernameOfRoomy);
    }
}