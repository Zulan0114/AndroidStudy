package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    private MyDatabaseHelper dbHelper;

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
//        Button createDatabase = findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(v -> dbHelper.getWritableDatabase());

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
            ContentValues values = new ContentValues();
            values.put("name", "A Clash of Kings");
            values.put("author", "George Martin");
            values.put("pages", 1040);
            values.put("price", 22.85);
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);

//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//            ContentValues values = new ContentValues();
//            values.put("name", "The Da Vinci Code");
//            values.put("author", "Dan Brown");
//            values.put("pages", 454);
//            values.put("price", 16.96);
//            db.insert("Book", null, values);
//
//            values.clear();
//            values.put("name", "The Lost Symbol");
//            values.put("author", "Dan Brown");
//            values.put("pages", 510);
//            values.put("price", 19.95);
//            db.insert("Book", null, values);
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.databasetest.provider/book/"+newId);
            ContentValues values = new ContentValues();
            values.put("name", "A Storm of Swords");
            values.put("pages", 1216);
            values.put("price", 24.05);
            getContentResolver().update(uri, values, null, null);

//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("price", 10.99);
//                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.databasetest.provider/book/"+newId);
            getContentResolver().delete(uri, null, null);

//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            db.delete("Book", "pages > ?", new String[]{"500"});
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("MainActivity", "book name is "+name);
                        Log.d("MainActivity", "book author is "+author);
                        Log.d("MainActivity", "book pages is "+pages);
                        Log.d("MainActivity", "book price is "+price);
                }
                cursor.close();
            }

//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                Cursor cursor = db.query("Book", null, null, null, null, null, null);
//                if (cursor.moveToFirst()) {
//                    do {
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        String author = cursor.getString(cursor.getColumnIndex("author"));
//                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
//                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
//
//                        Log.d("MainActivity", "book name is "+name);
//                        Log.d("MainActivity", "book author is "+author);
//                        Log.d("MainActivity", "book pages is "+pages);
//                        Log.d("MainActivity", "book price is "+price);
//                    } while (cursor.moveToNext());
//                }
//                cursor.close();
        });
    }
}