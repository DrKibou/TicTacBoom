package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button mpBtn, aiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mpBtn = (Button) findViewById(R.id.mpBtn);
        aiBtn = (Button) findViewById(R.id.aiBtn);

        mpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        Intent intent = new Intent();
    }

}