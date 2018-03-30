package com.assignments.sliit.heroesofourtime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignments.sliit.heroesofourtime.R;

public class WelcomePageActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        tv = (TextView) findViewById(R.id.tv);
        //iv = (ImageView) findViewById(R.id.iv);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.home_transition);
        tv.startAnimation(myAnim);
        //iv.startAnimation(myAnim);

        final Intent i =new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}