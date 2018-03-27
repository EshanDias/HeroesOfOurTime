package com.assignments.sliit.heroesofourtime.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.core.ImageTextList;
import com.assignments.sliit.heroesofourtime.dbAccess.DatabaseHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());

        List<Hero> heroList = db.getHeroes();

        db.closeDB();

        if (heroList == null) {
            Log.e(TAG, "No Heroes");
        }
        else {
            List<String> heroes = new ArrayList<>();
            List<Drawable> images = new ArrayList<>();
            for (Hero h : heroList) {
                heroes.add(h.getName());
                images.add(h.getHeroImage());
            }

//            ImageTextList adapter = new ImageTextList(MainActivity.this, heroes, images);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, heroes);

            ListView lv = findViewById(R.id.heroListView);

            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
