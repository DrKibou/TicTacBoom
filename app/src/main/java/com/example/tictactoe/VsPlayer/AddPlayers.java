package com.example.tictactoe.VsPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tictactoe.Home;
import com.example.tictactoe.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddPlayers extends AppCompatActivity {

    TextInputEditText playerOne, playerTwo;
    Button startBtn;
    ImageButton btnBack;
    String getPlayerOne, getPlayerTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerOne = findViewById(R.id.plyrOne);
        playerTwo = findViewById(R.id.plyrTwo);
        startBtn = findViewById(R.id.startBtn);
        btnBack = findViewById(R.id.backBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayerOne = playerOne.getText().toString();
                getPlayerTwo = playerTwo.getText().toString();

                if(getPlayerOne.isEmpty() && getPlayerTwo.isEmpty()){
                    Toast.makeText(AddPlayers.this, "Please enter your names", Toast.LENGTH_SHORT).show();
                } else {
                    openMulti();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
    }

    public void openMulti(){
        Intent intent = new Intent(getApplicationContext(), VsPlayer.class);
        intent.putExtra("playerone", getPlayerOne);
        intent.putExtra("playertwo", getPlayerTwo);
        startActivity(intent);
        finish();
    }
    public void openHome(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}