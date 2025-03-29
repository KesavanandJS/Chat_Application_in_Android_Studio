package com.example.chatapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {
    private Spinner countryCodeSpinner;
    private EditText contactNumber, contactName;
    private String selectedCountryCode;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        databaseHelper = new DatabaseHelper(this);
        countryCodeSpinner = findViewById(R.id.spinner_country_code);
        contactNumber = findViewById(R.id.et_contact_number);
        contactName = findViewById(R.id.et_contact_name);
        Button saveContactButton = findViewById(R.id.btn_save_contact);

        String[] countryCodes = {"+1 (USA)", "+91 (India)", "+44 (UK)", "+61 (Australia)", "+81 (Japan)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryCodes);
        countryCodeSpinner.setAdapter(adapter);

        countryCodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountryCode = countryCodes[position].split(" ")[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        saveContactButton.setOnClickListener(view -> {
            String contact = contactNumber.getText().toString().trim();
            String name = contactName.getText().toString().trim();

            if (contact.isEmpty() || name.isEmpty()) {
                Toast.makeText(NewContactActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = databaseHelper.insertContact(selectedCountryCode, contact, name);
            if (inserted) {
                Toast.makeText(NewContactActivity.this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                Log.d("NewContactActivity", "Contact Saved: " + selectedCountryCode + " " + contact + " - " + name);
                startActivity(new Intent(NewContactActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(NewContactActivity.this, "Error Saving Contact. Contact might already exist.", Toast.LENGTH_SHORT).show();
                Log.e("NewContactActivity", "Failed to save contact.");
            }
        });
    }
}
