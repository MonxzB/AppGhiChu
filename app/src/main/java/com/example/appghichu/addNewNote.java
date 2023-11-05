package com.example.appghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class addNewNote extends AppCompatActivity {
    EditText titleInput,contentInput;
    Button saveNoteBtn;
    Calendar calendar;
    String date;
    ArrayList<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        saveNoteBtn = findViewById(R.id.saveNoteBtn);
        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String content = contentInput.getText().toString();

                notes = new ArrayList<>();

                calendar = Calendar.getInstance();
                date = calendar.get(Calendar.DAY_OF_MONTH) +"/"+ (calendar.get(Calendar.MONTH)+1) +
                        "/"+ calendar.get(Calendar.YEAR);

                Note note= new Note(title,content,date);

                Database db = new Database(addNewNote.this);

                if (title.isEmpty()||content.isEmpty()){
                    Toast.makeText(addNewNote.this, "Không được để trống!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addNote(note);
                    finish();
                }
            }
        });
    }
}