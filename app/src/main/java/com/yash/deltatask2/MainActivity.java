package com.yash.deltatask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText gridHeight, gridWidth,number_players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.Btn_Start);
        gridHeight=findViewById(R.id.grid_height);
        gridWidth=findViewById(R.id.grid_width);
        number_players=findViewById(R.id.number_of_players);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                if(Integer.parseInt(number_players.getText().toString())<2||Integer.parseInt(number_players.getText().toString())>5){
                    number_players.setError("must be between 2-5");
                    if(Integer.parseInt(gridWidth.getText().toString())<2||Integer.parseInt(gridHeight.getText().toString())<2){
                        gridWidth.setError("must be greater than 2");
                        gridHeight.setError("must be greater than 2");
                    }
                }
                else{
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("grid_H",Integer.parseInt(gridHeight.getText().toString()));
                intent.putExtra("grid_W",Integer.parseInt(gridWidth.getText().toString()));
                intent.putExtra("number_of_players",Integer.parseInt(number_players.getText().toString()));
                startActivity(intent);}
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "please fill everything required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }

