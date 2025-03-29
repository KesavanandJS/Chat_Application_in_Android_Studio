package com.example.chatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MyContactsActivity extends AppCompatActivity {

    ListView listViewContacts;
    ArrayList<ContactModel> contactList;
    ContactAdapter contactAdapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);

        listViewContacts = findViewById(R.id.list_view_contacts);
        dbHelper = new DatabaseHelper(this);

        // Load contacts from database
        contactList = dbHelper.getAllContacts();
        contactAdapter = new ContactAdapter(this, contactList);
        listViewContacts.setAdapter(contactAdapter);
    }
}
