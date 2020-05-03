package com.yash.deltatask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText gridHeight, gridWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.Btn_Start);
        gridHeight=findViewById(R.id.grid_height);
        gridWidth=findViewById(R.id.grid_width);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("grid_H",Integer.parseInt(gridHeight.getText().toString()));
                intent.putExtra("grid_W",Integer.parseInt(gridWidth.getText().toString()));
                startActivity(intent);
            }
        });
    }
    }

