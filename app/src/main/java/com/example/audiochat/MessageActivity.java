package com.example.audiochat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText textInput;
    private TextView textChattingWith;
    private Button button_mic;
    private ImageView button_send;
    private ArrayList<Message> messages;
    String usernameOfRoomy , chatRoomID;

    private MediaRecorder mRecorder;
    private Button buttonRecord;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_message);
        recyclerView = findViewById(R.id.recyclerChat);
        textInput = findViewById(R.id.textChat);
        textChattingWith = findViewById(R.id.chattingPerson);
        button_send = findViewById(R.id.imageView);
        buttonRecord = findViewById(R.id.buttonRecord);
        messages = new ArrayList<>();
        usernameOfRoomy = getIntent().getStringExtra("usernames_of_room");
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/recorded_audio.3gp";
        textChattingWith.setText(usernameOfRoomy);
        setUpChat();
        buttonRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    startRecording();
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopRecording();
                }
                return false;
            }
        });
    }

    private void setUpChat(){
        FirebaseDatabase.getInstance().getReference("Users/"+ FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String myUsername = snapshot.getValue(User.class).getUsername();
                if(usernameOfRoomy.compareTo(myUsername)>0){
                    chatRoomID = myUsername + usernameOfRoomy;
                }else if(usernameOfRoomy.compareTo(myUsername)==0){
                    chatRoomID = myUsername + usernameOfRoomy;
                }else{
                    chatRoomID = usernameOfRoomy + myUsername;
                }
                attachMessage(chatRoomID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void attachMessage(String chatRoomID){
        FirebaseDatabase.getInstance().getReference("Messages/"+chatRoomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    messages.add(dataSnapshot.getValue(Message.class));
                }
                recyclerView.scrollToPosition(messages.size()-1);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void startRecording(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        }catch(IOException e){
            Log.e(LOG_TAG,"Prepare Failed");
        }
        mRecorder.start();
    }

    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}