package com.example.tictactoe.VsPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.tictactoe.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddPlayers extends AppCompatActivity {

    TextInputEditText playerOne, playerTwo;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerOne = findViewById(R.id.plyrOne);
        playerTwo = findViewById(R.id.plyrTwo);
        startBtn = findViewById(R.id.startBtn);

        playerOne.getText().toString();
        playerTwo.getText().toString();

    }

    public void openMulti(){
        Intent intent = new Intent();
    }
}