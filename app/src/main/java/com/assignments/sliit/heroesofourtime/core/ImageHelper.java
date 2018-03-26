package com.assignments.sliit.heroesofourtime.core;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by ASUS on 3/26/2018.
 */

public class ImageHelper {

    private static final String TAG = "ImageHelper";
    //This will convert the Drawable image to a byte array
    public byte[] insetImage(Drawable dbDrawable)  {
        Log.e(TAG, "inside image helper");
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();


    }
}
