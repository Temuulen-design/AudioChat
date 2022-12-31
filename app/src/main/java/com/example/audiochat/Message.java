package com.example.audiochat;

public class Message {
    private String sender;
    private String receiver;
    private String text;
    private String pathImage;

    public Message(){
    }

    public Message(String sender, String receiver, String text, String pathImage) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.pathImage = pathImage;
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

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
