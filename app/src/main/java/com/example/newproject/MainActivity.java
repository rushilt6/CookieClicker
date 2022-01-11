package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
        ImageView cookieImage;
        ConstraintLayout layout;
        TextView tc;
        AtomicInteger total = new AtomicInteger(0);
        Button gbutton;
        int grandmacost = 50;
        int grandmas = 0;
        TextView textcps;
        private Handler handler = new Handler();
        int cursors = 0;
        int cursorCost = 75;
        Button cbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cookieImage = findViewById(R.id.cookieImage);
        cookieImage.setImageResource(R.drawable.cookie);
        layout = findViewById(R.id.layout);
        tc = findViewById(R.id.tc);
        layout = findViewById(R.id.layout);
        gbutton = findViewById(R.id.gbutton);
        gbutton.setVisibility(View.INVISIBLE);
        textcps = findViewById(R.id.textcps);
        cbutton = findViewById(R.id.cbutton);
        cbutton.setVisibility(View.INVISIBLE);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        new Thread1().start();

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation increaseSize = new ScaleAnimation(.8f,1.0f,.8f,1.0f, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
                increaseSize.setDuration(200);
                AlphaAnimation fade = new AlphaAnimation(1.0f,0f);
                fade.setDuration(400);
                final TextView plus1 = new TextView(MainActivity.this);
                cookieImage.startAnimation(increaseSize);
                total.addAndGet(1);
                plus1.setId(View.generateViewId());
                plus1.setText("+1");
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
                plus1.setLayoutParams(params);
                layout.addView(plus1);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.connect(plus1.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT);
                constraintSet.connect(plus1.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT);
                constraintSet.connect(plus1.getId(),ConstraintSet.BOTTOM,layout.getId(),ConstraintSet.BOTTOM);
                constraintSet.connect(plus1.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP);
                int r = (int)(Math.random()*(5)+1);
                if(r==1) {
                    constraintSet.setVerticalBias(plus1.getId(), 0.20f);
                    constraintSet.setHorizontalBias(plus1.getId(), 0.30f);
                }
                if(r==2) {
                    constraintSet.setVerticalBias(plus1.getId(), 0.20f);
                    constraintSet.setHorizontalBias(plus1.getId(), 0.37f);
                }
                if(r==3) {
                    constraintSet.setVerticalBias(plus1.getId(), 0.20f);
                    constraintSet.setHorizontalBias(plus1.getId(), 0.47f);
                }
                if(r==4) {
                    constraintSet.setVerticalBias(plus1.getId(), 0.20f);
                    constraintSet.setHorizontalBias(plus1.getId(), 0.56f);
                }
                if(r==5) {
                    constraintSet.setVerticalBias(plus1.getId(), 0.20f);
                    constraintSet.setHorizontalBias(plus1.getId(), 0.64f);
                }
                ObjectAnimator up = ObjectAnimator.ofFloat(plus1,"translationY",-75f);
                up.start();
                plus1.startAnimation(fade);
                fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        layout.removeView(plus1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                constraintSet.applyTo(layout);
                if(total.get()<40 && grandmas==0)
                    gbutton.setVisibility(View.INVISIBLE);
                else
                    gbutton.setVisibility(View.VISIBLE);
                if(total.get()<65 && cursors==0)
                    cbutton.setVisibility(View.INVISIBLE);
                else
                    cbutton.setVisibility(View.VISIBLE);
                if(total.get()<grandmacost)
                    gbutton.setEnabled(false);
                else
                    gbutton.setEnabled(true);
                if(total.get()<cursorCost)
                    cbutton.setEnabled(false);
                else
                    cbutton.setEnabled(true);
                tc.setText("Cookies: "+total);
                Log.d("Tag",layout.getChildCount()+" Views");
            }
        });
        gbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.addAndGet(-grandmacost);
                tc.setText("Cookies: "+total);
                final ImageView grandmaImage = new ImageView(MainActivity.this);
                grandmaImage.setId(View.generateViewId());
                grandmaImage.setImageResource(R.drawable.grandma);
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(200,200);
                grandmaImage.setLayoutParams(params);
                layout.addView(grandmaImage);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.connect(grandmaImage.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT);
                constraintSet.connect(grandmaImage.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT);
                constraintSet.connect(grandmaImage.getId(),ConstraintSet.BOTTOM,layout.getId(),ConstraintSet.BOTTOM);
                constraintSet.connect(grandmaImage.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP);
                constraintSet.setVerticalBias(grandmaImage.getId(),.80f );
                constraintSet.setHorizontalBias(grandmaImage.getId(), .1f+(float)(grandmas*.1f));
                AlphaAnimation fade = new AlphaAnimation(0f,1.0f);
                fade.setDuration(700);
                grandmaImage.startAnimation(fade);
                constraintSet.applyTo(layout);
                grandmacost*=1.1;
                gbutton.setText("Grandma cost: "+grandmacost);
                grandmas++;
                textcps.setText("Cps: "+(grandmas+cursors));
                if(total.get()<grandmacost)
                    gbutton.setEnabled(false);
                else
                    gbutton.setEnabled(true);
                if(total.get()<cursorCost)
                    cbutton.setEnabled(false);
                else
                    cbutton.setEnabled(true);
                Log.d("Tag",grandmas+"");
            }
        });
        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.addAndGet(-cursorCost);
                tc.setText("Cookies: "+total);
                final ImageView cursorImage = new ImageView(MainActivity.this);
                cursorImage.setId(View.generateViewId());
                cursorImage.setImageResource(R.drawable.cursor);
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(200,200);
                cursorImage.setLayoutParams(params);
                layout.addView(cursorImage);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.connect(cursorImage.getId(),ConstraintSet.LEFT,layout.getId(),ConstraintSet.LEFT);
                constraintSet.connect(cursorImage.getId(),ConstraintSet.RIGHT,layout.getId(),ConstraintSet.RIGHT);
                constraintSet.connect(cursorImage.getId(),ConstraintSet.BOTTOM,layout.getId(),ConstraintSet.BOTTOM);
                constraintSet.connect(cursorImage.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP);
                constraintSet.setVerticalBias(cursorImage.getId(),.95f );
                constraintSet.setHorizontalBias(cursorImage.getId(), .1f+(float)((cursors/5)*.1f));
                AlphaAnimation fade = new AlphaAnimation(0f,1.0f);
                fade.setDuration(700);
                cursorImage.startAnimation(fade);
                constraintSet.applyTo(layout);
                cursorCost*=1.6;
                cbutton.setText("Cursor cost: "+cursorCost);
                cursors+=5;
                textcps.setText("Cps: "+(grandmas+cursors));
                if(total.get()<grandmacost)
                    gbutton.setEnabled(false);
                else
                    gbutton.setEnabled(true);
                if(total.get()<cursorCost)
                    cbutton.setEnabled(false);
                else
                    cbutton.setEnabled(true);
            }
        });
    }
    public class Thread1 extends Thread{
        public void run(){
            while(true){
                total.addAndGet(grandmas);
                total.addAndGet(cursors);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tc.setText("Cookies: "+total);
                        if(total.get()<grandmacost)
                            gbutton.setEnabled(false);
                        else
                            gbutton.setEnabled(true);
                        if(total.get()<cursorCost)
                            cbutton.setEnabled(false);
                        else
                            cbutton.setEnabled(true);
                    }
                });
            }
        }
    }
}