package com.example.revisionlab.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RevisionDatabase.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_USER);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_MESSAGE);
        onCreate(sqLiteDatabase);
    }
        //User table
        private static final String SQL_CREATE_ENTRIES_USER =
                "CREATE TABLE " + User.UserT.TABLE_NAME + " (" +
                        User.UserT._ID + " INTEGER PRIMARY KEY," +
                        User.UserT.COLUMN_1 + " TEXT," +
                        User.UserT.COLUMN_2 + " TEXT," +
                        User.UserT.COLUMN_3 + "TEXT)";

        private static final String SQL_DELETE_ENTRIES_USER =
                "DROP TABLE IF EXISTS " + User.UserT.TABLE_NAME ;

        //Message Table
        private static final String SQL_CREATE_ENTRIES_MESSAGE =
                "CREATE TABLE " + Message.MessageT.TABLE_NAME + " ("+
                        Message.MessageT._ID + "INTEGER PRIMARY KEY," +
                        Message.MessageT.COLUMN_1 + " TEXT," +
                        Message.MessageT.COLUMN_2 + " TEXT," +
                        Message.MessageT.COLUMN_3 + "TEXT)";

        private static final String SQL_DELETE_ENTRIES_MESSAGE =
                "DROP TABLE IF EXISTS " + Message.MessageT.TABLE_NAME;

     //add user
    public long addUser(String Username, String Password, String type ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //Create a new map of values where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.UserT.COLUMN_1, Username);
        contentValues.put(User.UserT.COLUMN_2, Password);
        contentValues.put(User.UserT.COLUMN_3, type);

        long newRowId = (sqLiteDatabase.insert(User.UserT.TABLE_NAME, null, contentValues));
        sqLiteDatabase.close();
        return newRowId;
    }

    //add messages
    public long addMessage(String username, String subject, String message){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Message.MessageT.COLUMN_1, username);
        contentValues.put(Message.MessageT.COLUMN_2, subject);
        contentValues.put(Message.MessageT.COLUMN_3, message);

        long newRowId = sqLiteDatabase.insert(Message.MessageT.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return newRowId;

    }
    //validate login
    public int validateUser(String username, String password){

        String[] projection = {
                User.UserT._ID
        };

        SQLiteDatabase db = this.getReadableDatabase();

        //Selection Criteria
        String selectionTeacher = User.UserT.COLUMN_1 + " =?"
                + " AND " + User.UserT.COLUMN_2 + "=?"
                + " AND " + User.UserT.COLUMN_3 + "=?";

        String selectionStudent = User.UserT.COLUMN_1 + " =?"
                + " AND " + User.UserT.COLUMN_2 + "=?"
                + " AND " + User.UserT.COLUMN_3 + "=?";

        String[] selectionArgsTeacher = {username, password, "Teacher"};
        String[] selectionArgsStudent = {username, password, "Student"};

        Cursor cursorTeacher = db.query(User.UserT.TABLE_NAME,
                projection,
                selectionTeacher,
                selectionArgsTeacher,
                null,
                null,
                null);

        Cursor cursorStudent = db.query(User.UserT.TABLE_NAME,
                projection,
                selectionStudent,
                selectionArgsStudent,
                null,
                null,
                null);

        int cursorCountTeacher = cursorTeacher.getCount();
        int cursorCountStudent = cursorStudent.getCount();

        cursorTeacher.close();
        cursorStudent.close();
        db.close();

        if(cursorCountTeacher > 0){
            return 1;
        }

        if(cursorCountStudent > 0){
            return 2;
        }
        else {
            return 0;
        }

    }
    //retrieve all the messages
    public List<Message> getAllmsgs(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                Message.MessageT.COLUMN_2,
                Message.MessageT.COLUMN_3,
                Message.MessageT.COLUMN_1
        };

        //sorting the messages in descending order by their primary key
        String sortOrder = Message.MessageT._ID + "DESC";

        Cursor cursor = db.query(
                Message.MessageT.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List messages = new ArrayList();

        while (cursor.moveToNext()) {
            //recheck
            Message msg = new Message();
            msg.setMessageID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageT._ID))));
            msg.setUn(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageT.COLUMN_1)));
            msg.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageT.COLUMN_2)));
            msg.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageT.COLUMN_3)));
            messages.add(msg);
        }
        cursor.close();
        db.close();

        return messages;
    }


}