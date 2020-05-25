package com.example.shoppingnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Listsdao {

    public void addList(DatabaseHelper helper, String listName){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("listName", listName);

        database.insertOrThrow("lists", null, values);
        database.close();
    }

    public ArrayList<Lists> allLists(DatabaseHelper helper){
        ArrayList<Lists> lists = new ArrayList<>();
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM lists", null);
        while (cursor.moveToNext()){
            Lists list = new Lists(cursor.getInt(cursor.getColumnIndex("list_id"))
                    , cursor.getString(cursor.getColumnIndex("listName")));

            lists.add(list);
        }
        return lists;
    }

    public void listNameDelete(DatabaseHelper helper, int list_id){
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete("lists","list_id=?",new String[]{String.valueOf(list_id)});
        database.close();
    }

    public void updateList(DatabaseHelper helper, int list_id, String listName){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("listName", listName);

        database.update("lists", values, "list_id=?", new String[]{String.valueOf(list_id)});
        database.close();
    }
}
