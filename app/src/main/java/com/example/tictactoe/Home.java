package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tictactoe.VsPlayer.AddPlayers;

public class Home extends AppCompatActivity {

    ImageButton mpBtn, aiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        mpBtn = (ImageButton) findViewById(R.id.mpBtn);
        aiBtn = (ImageButton) findViewById(R.id.aiBtn);

        mpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openMulti();
            }
        });

        aiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAi();
            }
        });
    }

    public void openAi(){
        Intent intent = new Intent(this, VsAI.class);
        startActivity(intent);
        finish();
    }
    public void openMulti(){
        Intent intent = new Intent(getApplicationContext(), AddPlayers.class);
        startActivity(intent);
        finish();
    }

}