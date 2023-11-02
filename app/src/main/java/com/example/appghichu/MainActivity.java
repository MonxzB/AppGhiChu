package com.example.appghichu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addNewNoteBtn;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewNoteBtn = findViewById(R.id.addNoteBtn);

        addNewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addNewNote.class);
                startActivity(intent);
            }
        });
        hien();
    }
    public void hien(){
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        Database db = new Database(MainActivity.this);
        notes = (ArrayList<Note>) db.getNote();

//        sử dụng Adapter đó để hiển thị dữ liệu trong danh sách
        Adapter adapter = new Adapter(MainActivity.this, notes);
        recyclerView.setAdapter(adapter);

        //Hiển thị recyclerview theo chiều dọc
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hien();
    }
}