package com.yash.deltatask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class splash_screen extends AppCompatActivity {
    private ImageView bottle;
    private boolean spinning;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        relativeLayout = findViewById(R.id.relativeLayout);
        bottle = findViewById(R.id.splash);
        spinBottle();
    }

    public void spinBottle() {
        if (!spinning) {
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;
            bottle.setX(pivotX);
            bottle.setY(pivotY);
            final Animation rotate = new RotateAnimation(0, 360, -300, 0);
            final Animation r = new RotateAnimation(0, 360, pivotX + 300, pivotY + 300);
            r.setDuration(1500);
            r.setFillAfter(true);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                    bottle.startAnimation(r);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            r.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                    Intent intent = new Intent(splash_screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            bottle.startAnimation(rotate);
        }
    }
}
