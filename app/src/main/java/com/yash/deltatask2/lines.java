package com.yash.deltatask2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class lines extends View {
    RelativeLayout rl;
    public static Paint[] p;
    float x, y;
    int k = 0, l = 0;
    int grid_width, grid_height;
    boolean out = false;

    public lines(Context context, RelativeLayout relativeLayout, float x_touch, float y_touch, int gridHeight, int gridWidth) {
        super(context);
        rl = relativeLayout;
        x = x_touch;
        y = y_touch;
        init();
        grid_width = gridWidth - 1;
        grid_height = gridHeight - 1;
    }

    void init() {
        p = new Paint[5];
        for (int i = 0; i < 5; i++) {
            p[i] = new Paint();
            p[i].setStrokeWidth(10);
        }
        p[0].setColor(Color.BLUE);
        p[1].setColor(Color.RED);
        p[2].setColor(Color.YELLOW);
        p[3].setColor(Color.GRAY);
        p[4].setColor(Color.CYAN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (float i = 100; i <= rl.getWidth() - 100 - (getWidth() - 200) / (grid_width); i += (getWidth() - 200) / (grid_width)) {
            for (float j = getHeight() / 4f; j < 3 * getHeight() / 4f; j += getHeight() / (2 * (grid_height))) {
                if (x > 100 && y > getHeight() / 4f - 50) {
                    k = (int) (i - 100) / ((getWidth() - 200) / (grid_width));
                    l = (int) (j - getHeight() / 4f) / (getHeight() / (2 * (grid_height)));
                    if (x > i && x < i + (getWidth() - 200) / (grid_width) && (y > j - 50 && y < j + 50) && (GameActivity.horizontal_lines[k][l] == 0)) {
                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.lineclick);
                        mp.start();
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }
                        });
                        GameActivity.horizontal_lines[k][l] = 1;
                        canvas.drawLine(i, j, i + (getWidth() - 200) / (grid_width), j, p[GameActivity.count % GameActivity.number_of_players]);
                        out = true;
                        if (GameActivity.count >= 0) {
                            if (l != grid_height && l != 0) {
                                if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l + 1] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l - 1] == 1 && GameActivity.vertical_lines[l - 1][k] == 1 && GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                        canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j - getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                        GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                        GameActivity.count_boxes++;
                                    }
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l - 1] == 1 && GameActivity.vertical_lines[l - 1][k] == 1 && GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j - getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                    GameActivity.count_boxes++;
                                } else {
                                    GameActivity.count++;
                                }
                            } else if (l == grid_height) {
                                if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l - 1] == 1 && GameActivity.vertical_lines[l - 1][k] == 1 && GameActivity.vertical_lines[l - 1][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j - getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else {
                                    GameActivity.count++;
                                }
                            } else {
                                if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l + 1] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else {
                                    GameActivity.count++;
                                }
                            }
                        }
                        break;
                    }
                    if (out) {
                        break;
                    }
                }
            }
        }
        for (float i = 100; i <= rl.getWidth() - 100; i += (getWidth() - 200) / (grid_width)) {
            for (float j = getHeight() / 4f; j < 3 * getHeight() / 4f - getHeight() / (2 * (grid_height)); j += getHeight() / (2 * (grid_height))) {
                if (x > 100 && y > getHeight() / 4f - 10) {
                    k = (int) (i - 100) / ((getWidth() - 200) / (grid_width));
                    l = (int) (j - getHeight() / 4f) / (getHeight() / (2 * (grid_height)));
                    if (y > j && y < j + getHeight() / (2 * (grid_height)) && (x > i - 50 && x < i + 50) && (GameActivity.vertical_lines[l][k] == 0)) {
                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.lineclick);
                        mp.start();
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                            }

                            ;
                        });
                        GameActivity.vertical_lines[l][k] = 1;
                        canvas.drawLine(i, j, i, j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                        out = true;
                        if (GameActivity.count >= 0) {
                            if (k != grid_width && k != 0) {
                                if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l + 1] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 && GameActivity.horizontal_lines[k - 1][l] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k - 1] == 1) {
                                        canvas.drawRect(i, j, i - (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                        GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                        GameActivity.count_boxes++;
                                    }
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 && GameActivity.horizontal_lines[k - 1][l] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k - 1] == 1) {
                                    canvas.drawRect(i, j, i - (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }

                                        ;
                                    });
                                } else {
                                    GameActivity.count++;
                                }
                            } else if (k == grid_width) {
                                if (GameActivity.horizontal_lines[k - 1][l + 1] == 1 && GameActivity.horizontal_lines[k - 1][l] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k - 1] == 1) {
                                    canvas.drawRect(i, j, i - (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else {
                                    GameActivity.count++;
                                }
                            } else {
                                if (GameActivity.horizontal_lines[k][l] == 1 && GameActivity.horizontal_lines[k][l + 1] == 1 && GameActivity.vertical_lines[l][k] == 1 && GameActivity.vertical_lines[l][k + 1] == 1) {
                                    canvas.drawRect(i, j, i + (getWidth() - 200) / (grid_width), j + getHeight() / (2 * (grid_height)), p[GameActivity.count % GameActivity.number_of_players]);
                                    GameActivity.players_win[GameActivity.count % GameActivity.number_of_players]++;
                                    GameActivity.count_boxes++;
                                    final MediaPlayer mpbox = MediaPlayer.create(getContext(), R.raw.boxcompletesound);
                                    mpbox.start();
                                    mpbox.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        public void onCompletion(MediaPlayer mp) {
                                            mpbox.release();
                                        }
                                    });
                                } else {
                                    GameActivity.count++;
                                }
                            }
                        }
                        break;
                    }
                    if (out) {
                        break;
                    }
                }
            }
        }
    }
}
