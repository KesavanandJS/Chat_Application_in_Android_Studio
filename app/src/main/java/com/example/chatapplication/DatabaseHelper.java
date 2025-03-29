package com.example.chatapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "ChatApp.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_USERS = "users";
    private static final String TABLE_CONTACTS = "contacts";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";


    private static final String COLUMN_CONTACT_ID = "contact_id";
    private static final String COLUMN_COUNTRY_CODE = "country_code";
    private static final String COLUMN_CONTACT_NUMBER = "contact_number";
    private static final String COLUMN_CONTACT_NAME = "contact_name";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);

        // Create Contacts Table
        String createContactsTable = "CREATE TABLE " + TABLE_CONTACTS + " (" +
                COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COUNTRY_CODE + " TEXT, " +
                COLUMN_CONTACT_NUMBER + " TEXT UNIQUE, " +
                COLUMN_CONTACT_NAME + " TEXT)";
        db.execSQL(createContactsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }


    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }


    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS +
                        " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    public boolean insertContact(String countryCode, String contactNumber, String contactName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNTRY_CODE, countryCode);
        values.put(COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(COLUMN_CONTACT_NAME, contactName);
        long result = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return result != -1;
    }


    public ArrayList<ContactModel> getAllContacts() {
        ArrayList<ContactModel> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        if (cursor.moveToFirst()) {
            do {
                String countryCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY_CODE));
                String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_NUMBER));
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_NAME));
                contactList.add(new ContactModel(countryCode, contactNumber, contactName));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }
}
