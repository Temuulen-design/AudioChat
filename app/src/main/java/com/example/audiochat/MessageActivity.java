package com.example.audiochat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private EditText textInput;
    private TextView textChattingWith;
    private ImageView button_send , chat;
    String usernameOfRoomy, chatRoomID;
    private Button buttonImage;
    private static final String LOG_TAG = "Record_log";

    private StorageReference imageStorage;
    private static final int GALLERY_PICK = 1;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;


    private String string = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        chat = findViewById(R.id.chat);
        recyclerView = findViewById(R.id.recyclerChat);
        textInput = findViewById(R.id.textChat);
        button_send = findViewById(R.id.imageView);
        buttonImage = (Button) findViewById(R.id.buttonImage);
        messageAdapter = new MessageAdapter(messages,getIntent().getStringExtra("messages"), MessageActivity.this);
        textChattingWith = findViewById(R.id.chattingPerson);
        usernameOfRoomy = getIntent().getStringExtra("usernames_of_room");
        textChattingWith.setText(usernameOfRoomy);
        messages = new ArrayList<>();

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
                alert.setTitle("Hidden text : ");
                alert.setMessage(string);
                alert.setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alert.show();
            }
        });
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select image"),GALLERY_PICK);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        string  = textInput.getText().toString();
        textInput.setText("");
        if(requestCode == GALLERY_PICK || resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            imageUri.parse("AudioChat/app/src/res/drawable-v24/a.png");
            chat.setImageURI(null);
            chat.setImageURI(imageUri);
            chat.setVisibility(View.VISIBLE);
        }


    }
}


