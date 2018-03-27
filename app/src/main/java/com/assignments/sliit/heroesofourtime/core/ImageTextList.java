package com.assignments.sliit.heroesofourtime.core;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignments.sliit.heroesofourtime.R;

import java.util.List;

/**
 * Created by esh_d on 28/03/2018.
 *
 */

public class ImageTextList extends ArrayAdapter<String>{
    private final Activity context;
    private final List<String> heroes;
    private final List<Drawable> imageId;

    public ImageTextList(Activity context, List<String> heroes, List<Drawable> images) {
        super(context, R.layout.list_view_row_image_text, heroes);
        this.context = context;
        this.heroes = heroes;
        this.imageId = images;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_view_row_image_text, null, true);

        TextView txtTitle = rowView.findViewById(R.id.textView_heroName);

        ImageView imageView = rowView.findViewById(R.id.imageView_HeroImage);

        txtTitle.setText(heroes.get(position));

        imageView.setImageResource(Integer.parseInt((imageId.get(position)).toString()));

        imageView.setImageResource(R.drawable.stephen_hawking_1942_present_300x200);

        return rowView;
    }
}