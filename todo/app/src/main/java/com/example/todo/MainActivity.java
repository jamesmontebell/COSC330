package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter;
    private SQLiteDatabase db;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getReadableDatabase();
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = getItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }
    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        db.execSQL("INSERT INTO Todo (todo) VALUES ('"+itemText+"');");
        etNewItem.setText("");
    }
    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }

    @SuppressLint("Range")
    private ArrayList<String> getItems()
    {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Todo";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do {
                String todoItem = c.getString(c.getColumnIndex("todo"));
                list.add(todoItem);
            }
            while(c.moveToNext());
        }
        c.close();
        return list;
    }
}