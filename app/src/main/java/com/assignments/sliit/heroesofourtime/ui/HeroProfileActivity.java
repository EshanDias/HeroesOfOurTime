package com.assignments.sliit.heroesofourtime.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.dbAccess.DatabaseHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HeroProfileActivity extends AppCompatActivity {

    DatabaseHelper db;
    Integer HeroId;
    Hero hero;
    Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        myContext = this.getApplicationContext();

        HeroId = getIntent().getIntExtra("HeroID", 0);

        db = new DatabaseHelper(getApplicationContext());

        try {
            hero = db.getHeroByTag(HeroId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setHero(hero);
    }

    @SuppressLint("SimpleDateFormat")
    private void setHero(Hero hero) {
        TextView tv_Name = findViewById(R.id.textView_heroName);
        TextView tv_Age = findViewById(R.id.textView_heroAge);
        TextView tv_Birthday = findViewById(R.id.textView_heroBirthday);
        TextView tv_Summary = findViewById(R.id.textView_heroSummary);
        TextView tv_Description = findViewById(R.id.textView_heroDescription);
        ImageView iv_Image = findViewById(R.id.imageView_HeroImage);
        String birthdayDeath;
        String death;
        String birthday = new SimpleDateFormat("yyyy-MM-dd").format(hero.getBirthday());
        death = ( hero.getDeath() == null ? "PRESENT" :
                (new SimpleDateFormat("yyyy-MM-dd").format(hero.getDeath())));

        iv_Image.setImageResource(hero.getHeroImage());
        tv_Name.setText(hero.getName());
        birthdayDeath = String.format("(%s - %s)", birthday, death);
        tv_Birthday.setText((birthdayDeath));

        tv_Age.setText(String.format("%s YEARS", String.valueOf(hero.CalculateAge())));
        tv_Summary.setText(hero.getSummary());
        tv_Description.setText(hero.getDescription());
    }

        public void addToFavourites(View view) {
            Toast.makeText(myContext,"Added To Favourites", Toast.LENGTH_SHORT).show();
        }

}
