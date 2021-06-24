package com.rootdata21.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student Info";
    private static final String TABLE_NAME = "classTwelve";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_Code = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_NAME+" TEXT, "+ KEY_NUMBER+" TEXT)";
        db.execSQL(Create_Table_Code);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS classTwelve");
        onCreate(db);
    }

    void addData(Contact_Info contact_info){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact_info.getName());
        contentValues.put(KEY_NUMBER, contact_info.getNumber());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }

    public List<Contact_Info> getAllData(){
        List<Contact_Info> allData = new ArrayList<Contact_Info>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query_code = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query_code,null);

        if (cursor.moveToFirst()){
            do{
                Contact_Info contact_info = new Contact_Info();
                contact_info.setId(Integer.parseInt(cursor.getString(0)));
                contact_info.setName((cursor.getString(1)));
                contact_info.setNumber((cursor.getString(2)));
                allData.add(contact_info);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return allData;
    }

    public int  updateContact(Contact_Info contact_info){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, contact_info.getId());
        contentValues.put(KEY_NAME, contact_info.getName());
        contentValues.put(KEY_NUMBER, contact_info.getNumber());

        return sqLiteDatabase.update(TABLE_NAME,contentValues, KEY_ID+" = ?",new String[]{String.valueOf(contact_info.getId())});
    }

    public void deleteContact(Contact_Info contact_info){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"id = ?",new String[]{String.valueOf(contact_info.getId())});
    }


}
