package com.example.tictactoe.VsPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tictactoe.Home;
import com.example.tictactoe.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddPlayers extends AppCompatActivity {

    TextInputEditText playerOne, playerTwo;
    ImageButton btnBack, startBtn;
    String getPlayerOne, getPlayerTwo;
    SwitchCompat switchMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        getSupportActionBar().hide();

        playerOne = findViewById(R.id.plyrOne);
        playerTwo = findViewById(R.id.plyrTwo);
        startBtn = findViewById(R.id.startBtn);
        btnBack = findViewById(R.id.backBtn);
        switchMode = findViewById(R.id.switch_id);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayerOne = playerOne.getText().toString();
                getPlayerTwo = playerTwo.getText().toString();

                if (getPlayerOne.isEmpty() && getPlayerTwo.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter players name", Toast.LENGTH_SHORT).show();
                } else if (getPlayerOne.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player one name", Toast.LENGTH_SHORT).show();
                } else if (getPlayerTwo.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player two name", Toast.LENGTH_SHORT).show();
                } else {
                    if(switchMode.isChecked()){
                        redirectActivity(AddPlayers.this, VsPlayerBomb.class);
                    }else{
                        redirectActivity(AddPlayers.this,VsPlayerClassic.class);
                    }
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
    public void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.putExtra("playerone", getPlayerOne);
        intent.putExtra("playertwo", getPlayerTwo);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }


    public void openHome() {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}