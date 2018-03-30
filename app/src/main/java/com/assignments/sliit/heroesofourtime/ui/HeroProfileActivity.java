package com.assignments.sliit.heroesofourtime.ui;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.dbAccess.DatabaseHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

public class HeroProfileActivity extends AppCompatActivity {

    DatabaseHelper db;
    Integer HeroId;
    Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        HeroId = getIntent().getIntExtra("HeroID", 0);

        db = new DatabaseHelper(getApplicationContext());

        hero = db.getHeroByTag(HeroId);

        TextView tv_Name = findViewById(R.id.textView_heroName);
        tv_Name.setText(hero.getName());

        TextView tv_Age = findViewById(R.id.textView_heroAge);
        tv_Age.setText(String.valueOf(hero.CalculateAge()));
    }
}
