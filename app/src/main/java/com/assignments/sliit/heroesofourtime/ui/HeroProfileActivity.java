package com.assignments.sliit.heroesofourtime.ui;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.dbAccess.DatabaseHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HeroProfileActivity extends AppCompatActivity {

    DatabaseHelper db;
    Integer HeroId;
    Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        TextView tv_Description = findViewById(R.id.textView_heroDescription);
        tv_Description.setMovementMethod(new ScrollingMovementMethod());

        HeroId = getIntent().getIntExtra("HeroID", 0);

        db = new DatabaseHelper(getApplicationContext());

        try {
            hero = db.getHeroByTag(HeroId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setHero(hero);
    }

    private void setHero(Hero hero) {
        TextView tv_Name = findViewById(R.id.textView_heroName);
        TextView tv_Age = findViewById(R.id.textView_heroAge);
        TextView tv_Birthday = findViewById(R.id.textView_heroBirthday);
        TextView tv_Death = findViewById(R.id.textView_heroDeath);
        TextView tv_Summary = findViewById(R.id.textView_heroSummary);
        TextView tv_Description = findViewById(R.id.textView_heroDescription);
        ImageView iv_Image = findViewById(R.id.imageView_HeroImage);

        iv_Image.setImageResource(hero.getHeroImage());
        tv_Name.setText(hero.getName());
        tv_Birthday.setText((new SimpleDateFormat("yyyy-MM-dd").format(hero.getBirthday())));
        tv_Death.setText( hero.getDeath() == null ? null :
                (new SimpleDateFormat("yyyy-MM-dd").format(hero.getDeath())));
        tv_Age.setText(String.valueOf(hero.CalculateAge()));
        tv_Summary.setText(hero.getSummary());
        tv_Description.setText(hero.getDescription());
    }
}
