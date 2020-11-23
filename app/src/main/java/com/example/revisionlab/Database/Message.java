package com.example.revisionlab.Database;

import android.provider.BaseColumns;

public final class Message {

    private int messageID;
    private String un, subject, message;

    public Message() {}

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getUn() {
        return un;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUn(String un) {
        this.un = un;
    }

    /* Inner class that defines the table contents */
    public static class MessageT implements BaseColumns {
        public static final String TABLE_NAME = "Message";
        public static final String COLUMN_1 = "User";
        public static final String COLUMN_2 = "Subject";
        public static final String COLUMN_3 = "Message";
    }
}