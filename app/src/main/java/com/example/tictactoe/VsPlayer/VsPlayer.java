package com.example.tictactoe.VsPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.Home;
import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VsPlayer extends AppCompatActivity {

    ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    Button btnReset, btnNewGame;
    ImageButton btnBack;

    TextView playerOneName, playerTwoName, txtWinner, scorep1, scorep2, p1Life, p2Life;

    List<int[]> combinations = new ArrayList<>();
    int[] boxpos = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};
    boolean isGameActive = true;
    boolean GameWin = false;

    int playerTurn = 1;
    int totalSelectedBox = 1;
    int scoreCounter1 = 0;
    int scoreCounter2 = 0;
    Random random;
    List<Integer> explodingTiles = new ArrayList<>();

    Player p1 = new Player();
    Player p2 = new Player();

    // check if tile is empty or not
    public boolean isBoxNotEmpty(int boxPosition) {

        boolean result = false;

        if (boxpos[boxPosition] == 0 || boxpos[boxPosition] == 3) {
            result = true;
        } else {
            isGameActive = false;
        }

        return result;
    }


    public void performAction(ImageView imageView, int selectedboxPos) {

        if (explodingTiles.contains(selectedboxPos)) {
            // this will explode the tile
            imageView.setImageResource(R.drawable.bomb);

            if (playerTurn == 1) {
                p1.setHp(p1.getHp() - 1);
                p1Life.setText("HP: " + p1.getHp());
                changeTurn(2);
                totalSelectedBox++;
                if (totalSelectedBox == 10) {
                    txtWinner.setText("Draw");
                    isGameActive = false;
                }
            } else if (playerTurn == 2) {
                p2.setHp(p2.getHp() - 1);
                p2Life.setText("HP: " + p2.getHp());
                changeTurn(1);
                totalSelectedBox++;
                if (totalSelectedBox == 10) {
                    txtWinner.setText("Draw");
                    isGameActive = false;
                }
            }
        } else {
            boxpos[selectedboxPos] = playerTurn;

            if (playerTurn == 1) {
                if (checkWinner()) {
                    imageView.setImageResource(R.drawable.x_colored);
                    txtWinner.setText(playerOneName.getText().toString() + " " + "has Won!");
                    scoreCounter1++;
                    scorep1.setText("Score:" + " " + scoreCounter1);
                    Toast.makeText(this, "Tap 'Reset' to begin next match.", Toast.LENGTH_SHORT).show();
                    if (scoreCounter1 == 3) {
                        txtWinner.setText(playerOneName.getText().toString() + "! " + "You're the Winner!");
                        Toast.makeText(this, "Tap 'NEW GAME' to begin New Game.", Toast.LENGTH_SHORT).show();
                        btnReset.setEnabled(false);
                    }
                    isGameActive = false;
                } else if (totalSelectedBox == 9) {
                    imageView.setImageResource(R.drawable.x_colored);
                    txtWinner.setText("Draw");
                    isGameActive = false;
                } else {
                    imageView.setImageResource(R.drawable.x_colored);
                    changeTurn(2);
                    totalSelectedBox++;
                }
            } else {

                if (checkWinner()) {
                    imageView.setImageResource(R.drawable.o_colored);
                    txtWinner.setText(playerTwoName.getText().toString() + " " + "has Won!");
                    scoreCounter2++;
                    scorep2.setText("Score:" + " " + scoreCounter2);
                    Toast.makeText(this, "Tap 'Reset' to begin next match.", Toast.LENGTH_SHORT).show();
                    if (scoreCounter2 == 3) {
                        txtWinner.setText(playerTwoName.getText().toString() + "! " + "You're the Winner!");
                        Toast.makeText(this, "Tap 'NEW GAME' to begin New Game.", Toast.LENGTH_SHORT).show();
                        btnReset.setEnabled(false);
                    }
                    isGameActive = false;
                } else if (totalSelectedBox == 9) {
                    imageView.setImageResource(R.drawable.o_colored);
                    txtWinner.setText("Draw");
                    isGameActive = false;
                } else {
                    imageView.setImageResource(R.drawable.o_colored);
                    changeTurn(1);
                    totalSelectedBox++;
                }
            }
        }
        if (p1.getHp() == 0) {
            txtWinner.setText(playerTwoName.getText().toString() + "! " + "You're the Winner!");
            btnReset.setEnabled(false);
            isGameActive = false;
        } else if (p2.getHp() == 0) {
            txtWinner.setText(playerOneName.getText().toString() + "! " + "You're the Winner!");
            btnReset.setEnabled(false);
            isGameActive = false;
        }
    }

    public boolean checkWinner() {
        boolean result = false;

        if (p1.getHp() == 0) {
            isGameActive = false;
            result = true;
        } else if (p2.getHp() == 0) {
            isGameActive = false;
            result = true;
        }

        for (int i = 0; i < combinations.size(); i++) {

            //it will get the index and check if the playerTurn is present at those indexes (combinations)
            int[] combi = combinations.get(i);

            if (boxpos[combi[0]] == playerTurn &&
                    boxpos[combi[1]] == playerTurn &&
                    boxpos[combi[2]] == playerTurn) {
                result = true;
                isGameActive = false;
            }
        }
        return result;
    }

    public void changeTurn(int currentPlayerPos) {
        playerTurn = currentPlayerPos;

        if (playerTurn == 1) {
            playerOneName.setTypeface(null, Typeface.BOLD);
            playerOneName.setTextColor(Color.WHITE);
            playerOneName.setBackgroundColor(Color.RED);
            playerTwoName.setTypeface(null, Typeface.NORMAL);
            playerTwoName.setTextColor(Color.BLACK);
            playerTwoName.setBackgroundColor(Color.TRANSPARENT);
        } else {
            playerTwoName.setTypeface(null, Typeface.BOLD);
            playerTwoName.setTextColor(Color.WHITE);
            playerTwoName.setBackgroundColor(Color.BLUE);
            playerOneName.setTypeface(null, Typeface.NORMAL);
            playerOneName.setTextColor(Color.BLACK);
            playerOneName.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * void bombTapped(ImageView imageView, int selectedPos, int playerWho){
     * <p>
     * if(boxpos[selectedPos] == 3){
     * if (explodingTiles.contains(selectedPos)){
     * if (playerWho == 1){
     * imageView.setImageResource(R.drawable.bomb);
     * p1.setHp(p1.getHp()-1);
     * <p>
     * } else if (playerWho == 2){
     * imageView.setImageResource(R.drawable.bomb);
     * p2.setHp(p2.getHp()-1);
     * }
     * }
     * }
     * }
     **/

    public void resetGame() {
        boxpos = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBox = 1;
        txtWinner.setText(" ");
        playerOneName.setTypeface(null, Typeface.BOLD);
        playerOneName.setTextColor(Color.WHITE);
        playerOneName.setBackgroundColor(Color.RED);
        playerTwoName.setTypeface(null, Typeface.NORMAL);
        playerTwoName.setTextColor(Color.BLACK);
        playerTwoName.setBackgroundColor(Color.TRANSPARENT);
        isGameActive = true;

        //this will create a two random tile
        random = new Random();
        explodingTiles.clear();
        while (explodingTiles.size() < 2) {
            int tile = random.nextInt(9);
            if (!explodingTiles.contains(tile)) {
                boxpos[tile] = 3;
                explodingTiles.add(tile);
            }
        }

        ((ImageView) findViewById(R.id.tile1)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile4)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile8)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile9)).setImageResource(R.drawable.empty);
    }

    public void newGame() {
        boxpos = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBox = 1;
        scoreCounter2 = 0;
        scoreCounter1 = 0;
        txtWinner.setText(" ");
        playerOneName.setTypeface(null, Typeface.BOLD);
        playerOneName.setTextColor(Color.WHITE);
        playerOneName.setBackgroundColor(Color.RED);
        playerTwoName.setTypeface(null, Typeface.NORMAL);
        playerTwoName.setTextColor(Color.BLACK);
        playerTwoName.setBackgroundColor(Color.TRANSPARENT);
        isGameActive = true;
        p1.setHp(5);
        p2.setHp(5);
        p1Life.setText("HP: " + p1.getHp());
        p2Life.setText("HP: " + p2.getHp());
        btnReset.setEnabled(true);

        scorep1.setText("Score: 0");
        scorep2.setText("Score: 0");
        //this will create a two random tile
        random = new Random();
        explodingTiles.clear();
        while (explodingTiles.size() < 2) {
            int tile = random.nextInt(9);
            if (!explodingTiles.contains(tile)) {
                boxpos[tile] = 3;
                explodingTiles.add(tile);
            }
        }

        ((ImageView) findViewById(R.id.tile1)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile4)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile8)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.tile9)).setImageResource(R.drawable.empty);
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
        scorep1 = findViewById(R.id.score1);
        scorep2 = findViewById(R.id.score2);
        p1Life = findViewById(R.id.p1Life);
        p2Life = findViewById(R.id.p2Life);
        btnNewGame = findViewById(R.id.btnNewGame);


        tile1 = findViewById(R.id.tile1);
        tile2 = findViewById(R.id.tile2);
        tile3 = findViewById(R.id.tile3);
        tile4 = findViewById(R.id.tile4);
        tile5 = findViewById(R.id.tile5);
        tile6 = findViewById(R.id.tile6);
        tile7 = findViewById(R.id.tile7);
        tile8 = findViewById(R.id.tile8);
        tile9 = findViewById(R.id.tile9);

        combinations.add(new int[]{0, 1, 2});
        combinations.add(new int[]{3, 4, 5});
        combinations.add(new int[]{6, 7, 8});
        combinations.add(new int[]{0, 3, 6});
        combinations.add(new int[]{1, 4, 7});
        combinations.add(new int[]{2, 5, 8});
        combinations.add(new int[]{2, 4, 6});
        combinations.add(new int[]{0, 4, 8});

        String p1N = getIntent().getStringExtra("playerone");
        String p2N = getIntent().getStringExtra("playertwo");

        playerOneName.setText(p1N);
        playerTwoName.setText(p2N);

        p1.setHp(5);
        p2.setHp(5);

        resetGame();

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

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
                resetGame();
            }
        });
        tile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(0)) {
                        performAction((ImageView) v, 0);
                    }
                }


            }
        });
        tile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(1)) {
                        performAction((ImageView) v, 1);
                    }
                }


            }
        });
        tile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(2)) {
                        performAction((ImageView) v, 2);
                    }

                }

            }
        });
        tile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(3)) {
                        performAction((ImageView) v, 3);
                    }
                }


            }
        });
        tile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(4)) {
                        performAction((ImageView) v, 4);
                    }
                }


            }
        });
        tile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(5)) {
                        performAction((ImageView) v, 5);
                    }
                }

            }
        });
        tile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(6)) {
                        performAction((ImageView) v, 6);
                    }
                }

            }
        });
        tile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(7)) {
                        performAction((ImageView) v, 7);
                    }
                }


            }
        });
        tile9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    if (isBoxNotEmpty(8)) {
                        performAction((ImageView) v, 8);
                    }
                }
            }
        });
    }
}