package com.yash.deltatask2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
@SuppressLint("ViewConstructor")
public class dotsAndBoxes extends View {
    RelativeLayout rl;
    Paint p,p_background;
    int grid_width,grid_height;
    public dotsAndBoxes(Context context, RelativeLayout relativeLayout,int gridHeight, int gridWidth) {
        super(context);
        rl=relativeLayout;
        p=new Paint();
        p_background=new Paint();
        p.setColor(Color.BLACK);
        grid_width=gridWidth-1;
        grid_height=gridHeight-1;
        p_background.setColor(Color.YELLOW);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(float i = 100;i<=rl.getWidth()-100;i+=(getWidth()-200)/(grid_width)) {
            for (float j = getHeight()/4f; j < 3*getHeight()/4f; j += getHeight()/(2*(grid_height))) {
                canvas.drawCircle(i, j, 17.5f, p);
            }
        }
    }
}
