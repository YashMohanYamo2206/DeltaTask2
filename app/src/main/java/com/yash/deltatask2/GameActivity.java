package com.yash.deltatask2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    public com.yash.deltatask2.lines L;
    RelativeLayout rl, main_rl;
    float x, y;
    Vibrator v;
    MediaPlayer mp;
    TextView player_1_win, player_2_win;
    int gridHeight, gridWidth;
    public static int count = 1, count_boxes = 0, number_of_players = 2;
    public static int[][] horizontal_lines;
    public static int[][] vertical_lines;
    public static int[] players_win;
    Button sound;
    private TextView player_3_win;
    private String clr;
    private TextView player_4_win, player_5_win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        count = 0;
        sound = findViewById(R.id.soundgame);
        // player_3_win=findViewById(R.id.player_3_win)
        gridHeight = intent.getIntExtra("grid_H", 5);
        gridWidth = intent.getIntExtra("grid_W", 5);
        if (intent.getIntExtra("number_of_players", 2) >= 2) {
            number_of_players = intent.getIntExtra("number_of_players", 2);
        }
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.mainactivitymusic);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            mp.start();
        }
        player_1_win = findViewById(R.id.player_1_win);
        player_2_win = findViewById(R.id.player_2_win);
        player_3_win = findViewById(R.id.player_3_win);
        player_4_win = findViewById(R.id.player_4_win);
        player_5_win = findViewById(R.id.player_5_win);
        players_win = new int[5];
        main_rl = findViewById(R.id.relativeLayout);
        horizontal_lines = new int[gridWidth - 1][gridHeight];
        vertical_lines = new int[gridHeight - 1][gridWidth];
        count_boxes = 0;
        //player_1_win.setText(players_win[0]);
        player_1_win.setTextColor(Color.BLUE);
        // player_3_win.setText(players_win[2]);
        player_3_win.setTextColor(Color.YELLOW);
        // player_2_win.setText(players_win[1]);
        player_2_win.setTextColor(Color.RED);
        // player_4_win.setText(players_win[3]);
        player_4_win.setTextColor(Color.GRAY);
        // player_5_win.setText(players_win[4]);
        player_5_win.setTextColor(Color.CYAN);
        //  player_1_win = findViewById(R.id.player_1_win);
        // player_2_win = findViewById(R.id.player_2_win);
        // player_1_win.setX(10);
        // player_1_win.setY((float) main_rl.getHeight()/8);
        //player_2_win.setY((float) main_rl.getHeight()/8);
        //player_2_win.setX((float)main_rl.getWidth()/number_of_players+10);
        dotsAndBoxes dab = new dotsAndBoxes(GameActivity.this, main_rl, gridHeight, gridWidth);
        main_rl.addView(dab);
        main_rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();
                lines lines = new lines(GameActivity.this, main_rl, x, y, gridHeight, gridWidth);
                main_rl.addView(lines);
                //     player_1_win.setText(String.valueOf(players_win[0]));
                //   player_2_win.setText(String.valueOf(players_win[1]));
                setText();
                if (count_boxes >= (gridHeight - 1) * (gridWidth - 1)) {
                    checkWinner();
                }
                //    tv_count.setText(String.valueOf(count_boxes));
                //   player_1_win.setTextColor(Color.BLUE);
                // player_2_win.setTextColor(Color.RED);
                return false;
            }
        });
    }

    private void setText() {
        switch (number_of_players) {
            case 2:
                player_1_win.setText(String.valueOf(players_win[0]));
                player_1_win.setTextColor(Color.BLUE);
                player_2_win.setText(String.valueOf(players_win[1]));
                player_2_win.setTextColor(Color.RED);
                player_3_win.setVisibility(View.INVISIBLE);
                player_4_win.setVisibility(View.INVISIBLE);
                player_5_win.setVisibility(View.INVISIBLE);
                break;
            case 3:
                player_1_win.setText(String.valueOf(players_win[0]));
                player_1_win.setTextColor(Color.BLUE);
                player_2_win.setText(String.valueOf(players_win[1]));
                player_2_win.setTextColor(Color.RED);
                player_3_win.setText(String.valueOf(players_win[2]));
                player_3_win.setTextColor(Color.YELLOW);
                player_4_win.setVisibility(View.INVISIBLE);
                player_5_win.setVisibility(View.INVISIBLE);
                break;
            case 4:
                player_1_win.setText(String.valueOf(players_win[0]));
                player_1_win.setTextColor(Color.BLUE);
                player_2_win.setText(String.valueOf(players_win[1]));
                player_2_win.setTextColor(Color.RED);
                player_3_win.setText(String.valueOf(players_win[2]));
                player_3_win.setTextColor(Color.YELLOW);
                player_4_win.setText(String.valueOf(players_win[3]));
                player_4_win.setTextColor(Color.GRAY);
                player_5_win.setVisibility(View.INVISIBLE);
                break;
            case 5:
                player_1_win.setText(String.valueOf(players_win[0]));
                player_1_win.setTextColor(Color.BLUE);
                player_2_win.setText(String.valueOf(players_win[1]));
                player_2_win.setTextColor(Color.RED);
                player_3_win.setText(String.valueOf(players_win[2]));
                player_3_win.setTextColor(Color.YELLOW);
                player_4_win.setText(String.valueOf(players_win[3]));
                player_4_win.setTextColor(Color.GRAY);
                player_5_win.setText(String.valueOf(players_win[4]));
                player_5_win.setTextColor(Color.CYAN);
                break;
        }
    }

    private void checkWinner() {
        int max_score = players_win[0], winner = 1;
        boolean draw = false, greater = false;
        String s = "draw between player " + winner;
        for (int i = 1; i < number_of_players; i++) {
            if (players_win[i] == max_score) {
                draw = true;
                s += " and " + (i + 1);
                winner = i + 1;
            } else if (players_win[i] > max_score) {
                max_score = players_win[i];
                winner = i + 1;
                draw = false;
            }
        }
        if (winner == 1) {
            clr = "BLUE";
        }
        if (winner == 2) {
            clr = "RED";
        }
        if (winner == 3) {
            clr = "YELLOW";
        }
        if (winner == 4) {
            clr = "GREY";
        }
        if (winner == 5) {
            clr = "CYAN";
        }
        //Toast.makeText(this, "winner is player-"+winner+"-color-"+ clr, Toast.LENGTH_SHORT).show();
        if (draw) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(s);
            dialog.setTitle("WINNER");
            dialog.setPositiveButton("NEW GAME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            dialog.setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("winner is player-" + winner + "\nColor " + clr);
            dialog.setTitle("WINNER");
            dialog.setPositiveButton("NEW GAME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            dialog.setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
    }

    public void music(View view) {
        if (mp != null) {
            mp.release();
            mp = null;
            sound.setBackgroundResource(R.drawable.ic_volume_off_black_24dp);
        } else {
            mp = MediaPlayer.create(this, R.raw.mainactivitymusic);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            mp.start();
            sound.setBackgroundResource(R.drawable.ic_volume_up_black_24dp);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp != null) {
            mp.pause();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (mp != null) {
            mp.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
            sound.setBackgroundResource(R.drawable.ic_volume_off_black_24dp);
        }
    }

    public void newGame(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void quit(View view) {
        finish();
    }
}