package com.example.appghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class update extends AppCompatActivity {

    EditText title_input, content_input;
    Button updateNoteBtn;
    String id, title, content, date;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH) +"/"+ calendar.get(Calendar.MONTH) +
                "/"+ calendar.get(Calendar.YEAR);

        title_input = findViewById(R.id.titleInput2);
        content_input = findViewById(R.id.contentInput2);
        updateNoteBtn = findViewById(R.id.updateNoteBtn);

        getData();

        updateNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database db =new Database(update.this);
                db.updatedata(id,title,content,date);
            }
        });
    }
    void getData(){
        if (getIntent().hasExtra("id")&&getIntent().hasExtra("title")&&
                getIntent().hasExtra("content")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");

            title_input.setText(title);
            content_input.setText(content);

        }else {
            Toast.makeText(this, "Ko có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}