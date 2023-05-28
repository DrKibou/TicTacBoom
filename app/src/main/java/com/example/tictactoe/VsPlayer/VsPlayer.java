package com.example.tictactoe.VsPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoe.Home;
import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

public class VsPlayer extends AppCompatActivity {

    ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    Button btnBack, btnReset;

    TextView playerOneName, playerTwoName, txtWinner;

    List<int[]> combinations = new ArrayList<>();
    int [] boxpos = {0,0,0,0,0,0,0,0,0};
    boolean isGameActive = true;

    int playerTurn = 1;
    int totalSelectedBox = 1;


    // check if tile is empty or not
    public boolean isBoxNotEmpty(int boxPosition){

        boolean result = false;

        if(boxpos[boxPosition] == 0){
            result = true;
        } else {
            isGameActive = false;
        }

        return result;
    }


    public void performAction(ImageView imageView, int selectedboxPos){

        boxpos[selectedboxPos] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.x);

            if (checkWinner()){
                txtWinner.setText(playerOneName.getText().toString() + " " + "has won");
            } else if (totalSelectedBox == 9){
                txtWinner.setText("Draw");
            } else {
                changeTurn(2);
                totalSelectedBox++;
            }
        } else {
            imageView.setImageResource(R.drawable.o);

            if (checkWinner()){
                txtWinner.setText(playerTwoName.getText().toString() + " " + "has won");
            } else if (totalSelectedBox == 9){
                txtWinner.setText("Draw");
            }
            else {
                changeTurn(1);
                totalSelectedBox++;
            }
        }

    }

    public boolean checkWinner(){
        boolean result = false;

        for (int i = 0; i < combinations.size();i++){

            int [] combi = combinations.get(i);

            if(boxpos[combi[0]] == playerTurn &&
                    boxpos[combi[1]] == playerTurn &&
                    boxpos[combi[2]] == playerTurn){
                result = true;
                isGameActive =false;
            }
        }
        return result;
    }

    public void changeTurn(int currentPlayerPos){
            playerTurn = currentPlayerPos;

            if (playerTurn == 1){
                playerOneName.setTypeface(null, Typeface.BOLD);
                playerTwoName.setTypeface(null, Typeface.NORMAL);
            } else {
                playerTwoName.setTypeface(null, Typeface.BOLD);
                playerOneName.setTypeface(null, Typeface.NORMAL);
            }
    }

    public void newGame(){
        boxpos = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBox = 1;
        txtWinner.setText(" ");
        playerOneName.setTypeface(null, Typeface.BOLD);
        playerTwoName.setTypeface(null, Typeface.NORMAL);
        isGameActive = true;

        ((ImageView) findViewById(R.id.tile1)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile2)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile3)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile4)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile5)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile6)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile7)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile8)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile9)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_player);

        playerOneName = findViewById(R.id.txtOne);
        playerTwoName = findViewById(R.id.txtTwo);
        txtWinner = findViewById(R.id.txtWinner);
        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);


        tile1 = findViewById(R.id.tile1);
        tile2 = findViewById(R.id.tile2);
        tile3 = findViewById(R.id.tile3);
        tile4 = findViewById(R.id.tile4);
        tile5 = findViewById(R.id.tile5);
        tile6 = findViewById(R.id.tile6);
        tile7 = findViewById(R.id.tile7);
        tile8 = findViewById(R.id.tile8);
        tile9 = findViewById(R.id.tile9);

        combinations.add(new int[] {0,1,2});
        combinations.add(new int[] {3,4,5});
        combinations.add(new int[] {6,7,8});
        combinations.add(new int[] {0,3,6});
        combinations.add(new int[] {1,4,7});
        combinations.add(new int[] {2,5,8});
        combinations.add(new int[] {2,4,6});
        combinations.add(new int[] {0,4,8});

        String p1N = getIntent().getStringExtra("playerone");
        String p2N = getIntent().getStringExtra("playertwo");

        playerOneName.setText(p1N);
        playerTwoName.setText(p2N);

        newGame();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPlayers.class);
                startActivity(intent);
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
        tile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(0)){
                        performAction((ImageView) v, 0);
                    }
                }


            }
        });
        tile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(1)){
                        performAction((ImageView) v, 1);
                    }
                }


            }
        });
        tile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(2)){
                        performAction((ImageView) v, 2);
                    }

                }

            }
        });
        tile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(3)){
                        performAction((ImageView) v, 3);
                    }
                }


            }
        });
        tile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(4)){
                        performAction((ImageView) v, 4);
                    }
                }


            }
        });
        tile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(5)){
                        performAction((ImageView) v, 5);
                    }
                }

            }
        });
        tile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(6)){
                        performAction((ImageView) v, 6);
                    }
                }

            }
        });
        tile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(7)){
                        performAction((ImageView) v, 7);
                    }
                }


            }
        });
        tile9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGameActive){
                    if(isBoxNotEmpty(8)){
                        performAction((ImageView) v, 8);
                    }
                }


            }
        });
    }
}