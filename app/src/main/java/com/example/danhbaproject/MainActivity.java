package com.example.danhbaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapter.ContactAdapter;
import com.example.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ContactAdapter contactAdapter;
    ArrayList<Contact> listContact;

    EditText txtName,txtPhone;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtName = this.<EditText>findViewById(R.id.txtName);
        txtPhone = this.<EditText>findViewById(R.id.txtPhone);
        btnSave = this.<Button>findViewById(R.id.btnSave);

        lvContact = this.<ListView>findViewById(R.id.lvContact);
        listContact = new ArrayList<>();

        contactAdapter = new ContactAdapter(
                MainActivity.this,
                R.layout.item,
                listContact);
        lvContact.setAdapter(contactAdapter);
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyLuu();
            }
        });

    }

    private void xuLyLuu() {
        Contact contact = new Contact(txtName.getText().toString(),txtPhone.getText().toString());
        listContact.add(contact);

        contactAdapter.notifyDataSetChanged();
    }
}