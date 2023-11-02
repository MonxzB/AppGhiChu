package com.example.appghichu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Context context;
    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "NotesDB.db";
    public static final String DB_TABLE = "NoteTable";

    public static final String COLUMN_ID = "NoteId";
    public static final String COLUMN_TITLE = "NoteTitle";
    public static final String COLUMN_CONTENT = "NoteContent";
    public static final String COLUMN_DATE = "NoteDate";
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_DATE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }
    void addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE,note.getTitle());
        contentValues.put(COLUMN_CONTENT,note.getContent());
        contentValues.put(COLUMN_DATE,note.getDate());

        long result = db.insert(DB_TABLE,null,contentValues);
        if (result == -1){
            Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
        }
    }
    public List<Note> getNote(){
        List<Note> listNote = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DB_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));

                listNote.add(note);

            }while (cursor.moveToNext());
        }

        return listNote;
    }
    void updatedata(String row_id,String title,String content,String date){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_CONTENT,content);
        cv.put(COLUMN_DATE,date);

        long result =db.update(DB_TABLE , cv ,"id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Lỗi cập nhật!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOnRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result =db.delete(DB_TABLE,"id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Lỗi",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Đã xóa",Toast.LENGTH_SHORT).show();
        }
    }


}
