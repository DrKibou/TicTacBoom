package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VsAI extends AppCompatActivity {

    ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    TextView txtCurrentTurn;
    Button btnBack, btnReset;

    char player = 'x';
    char computer = 'o';
    char gameBoard[][] = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
    boolean isGameActive = true;

    public void newGame(){
        isGameActive = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++){
                gameBoard[i][j] = '_';
            }
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.tile1)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile2)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile3)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile4)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile5)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile6)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile7)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile8)).setImageResource(0);
        ((ImageView) findViewById(R.id.tile9)).setImageResource(0);

        TextView status = findViewById(R.id.txtCurrentTurn);
        status.setText("X's Turn");
    }

    // check board if there's a winner
    public void checkWinner(char board[][]){

        // horizontal
        for(int row = 0; row < board.length; row++){
            if(board[row][0] == board[row][1] && board[row][0] == board[row][2]){
                if(board[row][0] == 'x'){
                    txtCurrentTurn.setText("X wins");
                    isGameActive = false;
                } else if (board[row][0] == 'o'){
                    txtCurrentTurn.setText("O wins");
                    isGameActive = false;
                }
            }
        }

        for(int col = 0; col < board.length; col++){
            if(board[0][col] == board[1][col] && board[0][col] == board[2][col]){
                if(board[0][col] == 'x'){
                    txtCurrentTurn.setText("X wins");
                    isGameActive = false;
                } else if (board[0][col] == 'o'){
                    txtCurrentTurn.setText("O wins");
                    isGameActive = false;
                }
            }
        }

        if(board[0][0] == board[1][1] & board[0][0] == board[2][2]){
            if(board[0][0] == 'x'){
                txtCurrentTurn.setText("X wins");
                isGameActive = false;
            } else if(board[0][0] == 'o'){
                txtCurrentTurn.setText("O wins");
                isGameActive = false;
            }
        }
        if(board[2][0] == board[1][1] & board[2][0] == board[2][2]){
            if(board[2][0] == 'x'){
                txtCurrentTurn.setText("X wins");
                isGameActive = false;
            } else if(board[2][0] == 'o'){
                txtCurrentTurn.setText("O wins");
                isGameActive = false;
            }
        }
        if(isMovesLeft(gameBoard)==false){
            txtCurrentTurn.setText("Draw.");
        }
    }

    public Boolean isMovesLeft(char board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    int evaluate(char b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == computer)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == computer)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == computer)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == computer)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    int minimax(char board[][],
                int depth, Boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft(board) == false)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]=='_')
                    {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == '_')
                    {
                        // Make the move
                        board[i][j] = computer;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    move findBestMove(char board[][])
    {
        int bestVal = -1000;
        move bestMove = new move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == '_')
                {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    public void compMove(int row, int col){
        switch(row){
            case 0:
                switch(col){
                    case 0:
                        tile1.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                    break;
                    case 1:
                        tile2.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                    break;
                    case 2:
                        tile3.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                    break;
                }
            break;
            case 1:
                switch(col){
                    case 0:
                        tile4.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                    case 1:
                        tile5.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                    case 2:
                        tile6.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                }
            break;
            case 2:
                switch(col){
                    case 0:
                        tile7.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                    case 1:
                        tile8.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                    case 2:
                        tile9.setImageResource(R.drawable.o);
                        gameBoard[row][col] = 'o';
                        break;
                }
                break;
        }
    }

    public void back2Home(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_ai);

        tile1 = (ImageView) findViewById(R.id.tile1);
        tile2 = (ImageView) findViewById(R.id.tile2);
        tile3 = (ImageView) findViewById(R.id.tile3);
        tile4 = (ImageView) findViewById(R.id.tile4);
        tile5 = (ImageView) findViewById(R.id.tile5);
        tile6 = (ImageView) findViewById(R.id.tile6);
        tile7 = (ImageView) findViewById(R.id.tile7);
        tile8 = (ImageView) findViewById(R.id.tile8);
        tile9 = (ImageView) findViewById(R.id.tile9);

        txtCurrentTurn = (TextView) findViewById(R.id.txtCurrentTurn);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnReset = (Button) findViewById(R.id.btnReset);

        newGame();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back2Home();
            }
        });

        tile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[0][0] == '_'){
                        tile1.setImageResource(R.drawable.x);
                        gameBoard[0][0] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[0][1] == '_'){
                        tile2.setImageResource(R.drawable.x);
                        gameBoard[0][1] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[0][2] == '_'){
                        tile3.setImageResource(R.drawable.x);
                        gameBoard[0][2] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[1][0] == '_'){
                        tile4.setImageResource(R.drawable.x);
                        gameBoard[1][0] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[1][1] == '_'){
                        tile5.setImageResource(R.drawable.x);
                        gameBoard[1][1] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[1][2] == '_'){
                        tile6.setImageResource(R.drawable.x);
                        gameBoard[1][2] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[2][0] == '_'){
                        tile7.setImageResource(R.drawable.x);
                        gameBoard[2][0] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[2][1] == '_'){
                        tile8.setImageResource(R.drawable.x);
                        gameBoard[2][1] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });
        tile9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGameActive){
                    if(gameBoard[2][2] == '_'){
                        tile9.setImageResource(R.drawable.x);
                        gameBoard[2][2] = 'x';
                        move comp = findBestMove(gameBoard);
                        compMove(comp.row, comp.col);
                        checkWinner(gameBoard);
                    }
                }
            }
        });

    }

}