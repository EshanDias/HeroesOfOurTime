package com.assignments.sliit.heroesofourtime.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.assignments.sliit.heroesofourtime.R;
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

        //Creating Heroes
        Hero hero = new Hero("STEVE JOBS");
        Hero hero2 = new Hero("NELSON MANDELA");
        Hero hero3 = new Hero("STEPHEN HAWKING");


        //Insert Hero to the db
        long heroId = db.insertHero(hero);
        if (heroId > 0) {
            Log.e(TAG, "Hero1 Successfully Saved");
        }

        long hero2Id = db.insertHero(hero2);
        if (hero2Id > 0) {
            Log.e(TAG, "Hero2 Successfully Saved");
        }
        long hero3Id = db.insertHero(hero3);

        List<Hero> heroList = db.getHeroes();

        db.closeDB();

        if (heroList == null) {
            Log.e(TAG, "No Heroes");
        }
        else {
            List<String> heroes = new ArrayList<>();
            for (Hero h : heroList) {
                heroes.add(h.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, heroes);

            ListView lv = findViewById(R.id.listView);

            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
