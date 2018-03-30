package com.assignments.sliit.heroesofourtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.core.ImageTextList;
import com.assignments.sliit.heroesofourtime.dbAccess.DatabaseHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper db;

    ListView list;
    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        try {
            heroList = db.getHeroes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        db.closeDB();

        if (heroList == null) {
            Log.e(TAG, "No Heroes");
        } else {

            ImageTextList adapter = new ImageTextList(MainActivity.this, heroList);
            list = findViewById(R.id.heroListView);
            list.setAdapter(adapter);

            //This is to check the row click event
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(MainActivity.this, "You Clicked at " + heroList.get(position).getName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, HeroProfileActivity.class);
                    intent.putExtra("HeroID", heroList.get(position).getHeroID());
                    MainActivity.this.startActivity(intent);
                }
            });
        }
    }
}
