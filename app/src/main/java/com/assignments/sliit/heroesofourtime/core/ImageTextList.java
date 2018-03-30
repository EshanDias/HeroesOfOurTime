package com.assignments.sliit.heroesofourtime.core;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignments.sliit.heroesofourtime.R;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by esh_d on 28/03/2018.
 *
 */

public class ImageTextList extends ArrayAdapter<Hero>{
    private final Activity context;
    private final List<Hero> heroes;

    public ImageTextList(Activity context, List<Hero> heroes) {
        super(context, R.layout.list_view_row_image_text, heroes);
        this.context = context;
        this.heroes = heroes;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_view_row_image_text, null, true);

        TextView txtTitle = rowView.findViewById(R.id.textView_heroName);
        ImageView imageView = rowView.findViewById(R.id.imageView_HeroImage);
        TextView textView_HeroId = rowView.findViewById(R.id.textView_HeroId);

        txtTitle.setText(heroes.get(position).getName());
        imageView.setImageResource(heroes.get(position).getHeroImage());
        textView_HeroId.setText(String.valueOf(heroes.get(position).getHeroID()));

        return rowView;
    }
}