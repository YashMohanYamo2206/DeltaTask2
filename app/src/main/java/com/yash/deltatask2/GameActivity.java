package com.yash.deltatask2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class GameActivity extends AppCompatActivity {
    public com.yash.deltatask2.lines L;
    RelativeLayout rl,main_rl;
    float x,y;
    TextView player_1_win,player_2_win;
    int gridHeight,gridWidth;
    public static int count =1,number_of_players=2;
    public static int[][] horizontal_lines;
    public static int[][] vertical_lines;
    public static int[] players_win;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        count=0;
        gridHeight=intent.getIntExtra("grid_H",5);
        gridWidth=intent.getIntExtra("grid_W",5);
        if(intent.getIntExtra("number_of_players",2)>=2){
            number_of_players=intent.getIntExtra("number_of_players",2);
        }
        players_win=new int[number_of_players];
        main_rl=findViewById(R.id.relativeLayout);
        horizontal_lines=new int[gridWidth-1][gridHeight];
        vertical_lines=new int[gridHeight-1][gridWidth];
        //boxes=new int[gridWidth-1][gridHeight-1];
        player_1_win=findViewById(R.id.player_1_win);
        player_2_win=findViewById(R.id.player_2_win);
        dotsAndBoxes dab=new dotsAndBoxes(GameActivity.this,main_rl,gridHeight,gridWidth);
        main_rl.addView(dab);
        main_rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x=event.getX();
                y=event.getY();
                lines lines = new lines(GameActivity.this,main_rl,x,y,gridHeight,gridWidth);
                main_rl.addView(lines);
                player_1_win.setText(String.valueOf(players_win[0]));
                player_2_win.setText(String.valueOf(players_win[1]));
                return false;
            }
        });
    }
}