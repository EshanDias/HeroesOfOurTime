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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper db;
    Integer imageRes[] = {R.drawable.nelson_mandela, R.drawable.stephen_hawking,R.drawable.abdul_kalam,
            R.drawable.steve_jobs,R.drawable.oprah_winfrey,R.drawable.warren_buffett,R.drawable.bill_gates,
            R.drawable.pele,R.drawable.angela_merkel,R.drawable.mark_zuckerberg,};
    ListView list;
    List<Hero> heroList;
    List<String> heroNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        heroList = db.getHeroes();
        db.closeDB();
        heroNameList = new ArrayList<>(heroList.size());

        if (heroList == null) {
            Log.e(TAG, "No Heroes");
        } else {
            for (Hero h : heroList) {
                heroNameList.add(h.getName());
            }

            ImageTextList adapter = new ImageTextList(MainActivity.this, heroNameList, imageRes);
            list = findViewById(R.id.heroListView);
            list.setAdapter(adapter);

            //This is to check the row click event
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(MainActivity.this, "You Clicked at " + heroNameList.get(position), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, HeroProfileActivity.class);
                    intent.putExtra("HeroID", position+1);
                    MainActivity.this.startActivity(intent);
                }
            });
        }
    }
}
