package com.example.tic_tac_toe_app_java_project_psk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import AI.AIUtil;
/**
 * Klasa zawwierająca przyciski i wykonująca kcje nawigacji
 * **/
public class Navi extends AppCompatActivity  {
    private Button playPvP, playWAi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_navi2);
        /**
         * @param playPvP przycisk służacy do wyboru rozgrywki jeden nan jeden
         * **/
        playPvP = (Button) findViewById(R.id.play1x1);
        playPvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navi.this, MainActPvP.class));
            }
        });
        /**
         * @param playWAi przycisk służacy do wyboru rozgrywki z komputerem jako przeciwnikiem
         * **/
        playWAi = (Button) findViewById(R.id.playWithComputer);
        playWAi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navi.this, MainActivity.class));
            }

        });

    }


}