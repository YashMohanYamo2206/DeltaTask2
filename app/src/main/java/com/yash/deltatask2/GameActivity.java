package com.yash.deltatask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;




public class GameActivity extends AppCompatActivity {
    public com.yash.deltatask2.lines L;
    RelativeLayout rl,main_rl;
    float x,y;
    int gridHeight,gridWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        gridHeight=intent.getIntExtra("grid_H",5);
        gridWidth=intent.getIntExtra("grid_W",5);
        main_rl=findViewById(R.id.relativeLayout);
        dotsAndBoxes dab=new dotsAndBoxes(GameActivity.this,main_rl,gridHeight,gridWidth);
        main_rl.addView(dab);
        main_rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x=event.getX();
                y=event.getY();
                lines lines = new lines(GameActivity.this,main_rl,x,y,gridHeight,gridWidth);
                main_rl.addView(lines);
                return false;
            }
        });
    }
}
