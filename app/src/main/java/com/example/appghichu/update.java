package com.example.appghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class update extends AppCompatActivity {

    EditText showTitle, showContent;
    Button updateNoteBtn;
    int id;
    String title,content,newDate;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        calendar = Calendar.getInstance();

        showTitle = findViewById(R.id.showTitle);
        showContent = findViewById(R.id.showContent);
        updateNoteBtn = findViewById(R.id.updateNoteBtn);

        getIntentData();
        updateNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newDate = calendar.get(Calendar.DAY_OF_MONTH) +"/"+ (calendar.get(Calendar.MONTH)+1) +
                        "/"+ calendar.get(Calendar.YEAR);
                Database db =new Database(update.this);
                title = showTitle.getText().toString();
                content = showContent.getText().toString();
                db.update(id,title,content,newDate);
                finish();
            }
        });
    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("content")){
            //Lấy DL từ Intent
            id = getIntent().getIntExtra("id",0);
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");
            //Lưu DL vào Intent
            showTitle.setText(title);
            showContent.setText(content);
            Log.d("stev", title+" "+content);
        }else {
            Toast.makeText(this, "Ko có DL!!!", Toast.LENGTH_SHORT).show();
        }
    }
}