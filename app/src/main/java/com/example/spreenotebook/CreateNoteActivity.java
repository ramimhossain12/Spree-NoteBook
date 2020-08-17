package com.example.spreenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spreenotebook.DbHelper.NoteDbHelper;

public class CreateNoteActivity extends AppCompatActivity {

    Button btnSave;
    EditText editTextNewNote;
    NoteDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);


        btnSave = findViewById(R.id.btnSave);
        editTextNewNote = findViewById(R.id.editTextNewNote);

        dbHelper = new NoteDbHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = editTextNewNote.getText().toString();
                if (!TextUtils.isEmpty(note)) {
                    if (dbHelper.addData(note)) {
                        Toast.makeText(getApplicationContext(), "Note added", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(CreateNoteActivity.this, NotesActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}