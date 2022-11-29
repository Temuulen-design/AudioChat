package com.example.audiochat;

public class Message {
    private String sender;
    private String receiver;
    private String text;
    private String audioPath;

    public Message() {
    }

    public Message(String sender, String receiver, String text, String audioPath) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.audioPath = audioPath;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }
}
