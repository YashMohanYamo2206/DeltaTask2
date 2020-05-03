package com.yash.deltatask2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class lines extends View {
    RelativeLayout rl;
    Paint p1,p2;
    float x,y;
    int grid_width,grid_height;
    boolean out = false;
    int player1=1;
    public lines(Context context, RelativeLayout relativeLayout, float x_touch, float y_touch,int gridHeight,int gridWidth) {
        super(context);


        rl=relativeLayout;


        x=x_touch;
        y=y_touch;


        p1=new Paint();
        p2=new Paint();


        p1.setColor(Color.BLUE);
        p2.setColor(Color.RED);


        p1.setStrokeWidth(10);
        p2.setStrokeWidth(10);


        grid_width=gridWidth-1;
        grid_height=gridHeight-1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(float i = 100;i < rl.getWidth()-100 -(getWidth()-200)/(grid_width) ;i+=(getWidth()-200)/(grid_width)) {
            for (float j = getHeight()/4f; j < 3*getHeight()/4f; j += getHeight()/(2*(grid_height))) {
                if(x>100 && y>getHeight()/4f- 10) {
                    if(x>i && x<i+(getWidth()-200)/(grid_width) && (y>j-50 && y<j+50)) {
                        if(player1==1){

                            canvas.drawLine(i,j,i+(getWidth()-200)/(grid_width),j,p1);
                            out=true;
                            player1=2;
                            break;
                        }
                        else {
                            player1=1;
                            canvas.drawLine(i,j,i+(getWidth()-200)/(grid_width),j,p2);
                            out=true;
                            player1=1;
                            break;
                        }

                    }
                    if(out){
                        break;
                    }
                }
            }
        }
        for(float i = 100;i <= rl.getWidth()-100  ;i+=(getWidth()-200)/(grid_width)) {
            for (float j = getHeight()/4f; j < 3*getHeight()/4f - getHeight()/(2*(grid_height)); j += getHeight()/(2*(grid_height))) {
                if(x>100 && y>getHeight()/4f- 10) {
                    if(y>j && y<j+getHeight()/(2*(grid_height)) && (x>i-50 && x<i+50)) {
                        if(player1==1){

                            canvas.drawLine(i,j,i,j+getHeight()/(2*(grid_height)),p1);
                            out=true;
                            player1=2;
                            break;
                        }
                        else{

                            canvas.drawLine(i,j,i,j+getHeight()/(2*(grid_height)),p2);
                            out=true;
                            player1=1;
                            break;
                        }
                    }
                    if(out){
                        break;
                    }
                }
            }
        }
    }
}
