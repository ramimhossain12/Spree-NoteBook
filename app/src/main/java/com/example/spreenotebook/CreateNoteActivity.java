package com.example.spreenotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spreenotebook.DbHelper.NoteDbHelper;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, exitButton;
    EditText editTextNewNote;
    NoteDbHelper dbHelper;
    private android.app.AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);


        btnSave = findViewById(R.id.btnSave);
        exitButton = findViewById(R.id.buttonID);
        exitButton.setOnClickListener(this);
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



    //menu item find

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (item.getItemId()==R.id.shareID){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/type");
            String subject = "Note_Book app";
            String body ="This app  is very useful .\n com.example.spreenotebook";
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent,"share with"));


        }else if (item.getItemId()==R.id.feedbackID){
            Intent intent = new Intent(getApplicationContext(),FeadbackActivity.class);
            startActivity(intent);




        }else if (id == R.id.aboutId){
            Intent intent = new Intent(this,about.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.dateId){
            Intent intent = new Intent(this,DatePicker.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        alertDialogBuilder = new android.app.AlertDialog.Builder(CreateNoteActivity.this);
        alertDialogBuilder.setTitle(R.string.title_Text);

        alertDialogBuilder.setMessage(R.string.message_Text);

        alertDialogBuilder.setIcon(R.drawable.pic);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });


        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(CreateNoteActivity.this, "You have clicked on cancel button", Toast.LENGTH_SHORT).show();

            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    public void onBackPressed() {

        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want Exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        CreateNoteActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}