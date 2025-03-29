package com.example.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        Button logoutButton = findViewById(R.id.btn_logout);
        Button chatButton = findViewById(R.id.btn_chat);
        Button newContactButton = findViewById(R.id.btn_new_contact);
        Button myContactsButton = findViewById(R.id.btn_my_contacts);

        logoutButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        chatButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ChatActivity.class)));

        newContactButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, NewContactActivity.class)));

        myContactsButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, MyContactsActivity.class)));
    }
}
