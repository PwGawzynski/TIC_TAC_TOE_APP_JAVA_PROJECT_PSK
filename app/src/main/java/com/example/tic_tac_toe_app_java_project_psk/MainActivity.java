package com.example.tic_tac_toe_app_java_project_psk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import AI.AIUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;
    private int playerOneScoreCount, playerTwoScoreCount, roundCount=1;
    boolean activePlayer;
    Handler handler;
    Boolean toggle;

    //p1 => 0
    //p2 => 1
    // empty => 2
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, //rows
            {0,3,6}, {1,4,7}, {2,5,8}, //columns
            {0,4,8}, {2,4,6} //cross
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame = (Button) findViewById(R.id.resetGame);

        for(int i = 0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
        handler = new Handler();
        toggle = false;
    }

    @Override
    public void onClick(View v) {
        if(toggle){
            toggle=false;
            roundCount=0;
        }
        if(!((Button)v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId()); //btn_2
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length())); //2
        if(activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#C3F44E")); // color X
            gameState[gameStatePointer] = 0;
            Button btn;
            activePlayer = !activePlayer;
            switch (AIUtil.process(gameState)){
                case 0:
                    btn = (Button) findViewById(R.id.btn_0);
                    btn.performClick();
                    break;
                case 1:
                    btn = (Button) findViewById(R.id.btn_1);
                    btn.performClick();

                    break;
                case 2:
                    btn = (Button) findViewById(R.id.btn_2);
                    btn.performClick();

                    break;
                case 3:
                    btn = (Button) findViewById(R.id.btn_3);
                    btn.performClick();

                    break;
                case 4:
                    btn = (Button) findViewById(R.id.btn_4);
                    btn.performClick();

                    break;
                case 5:
                    btn = (Button) findViewById(R.id.btn_5);
                    btn.performClick();

                    break;
                case 6:
                    btn = (Button) findViewById(R.id.btn_6);
                    btn.performClick();

                    break;
                case 7:
                    btn = (Button) findViewById(R.id.btn_7);
                    btn.performClick();

                    break;
                case 8:
                    btn = (Button) findViewById(R.id.btn_8);
                    btn.performClick();

                    break;
                default:
                    break;
            }
            activePlayer = !activePlayer;
        }else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FF5652")); // color O
            gameState[gameStatePointer] = 1;
        }

        roundCount++;

        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player One Won!", Toast.LENGTH_SHORT).show();
                roundCount=0;
                playAgain();
            }else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Two Won!", Toast.LENGTH_SHORT).show();
                roundCount=0;
                toggle =true;
                playAgain();
            }
        }else if(roundCount == 9) {
            playAgain();
            Toast.makeText(this,"No Winner!", Toast.LENGTH_SHORT).show();
        }else {
            activePlayer = !activePlayer;
        }
        if(playerOneScoreCount > playerTwoScoreCount) {
            playerStatus.setText("Player One is Winning!");
        }else if(playerTwoScoreCount > playerOneScoreCount) {
            playerStatus.setText("Player Two is Winning!");
        }else{
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }

    public boolean checkWinner() {
        boolean winnerResult = false;
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {
                winnerResult = true;
            }
        }
        return winnerResult;
    }

        public void updatePlayerScore(){
            playerOneScore.setText(Integer.toString(playerOneScoreCount));
            playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
        }

        public void playAgain(){
            roundCount = 0;
            activePlayer = true;
            for(int i = 0; i < buttons.length; i++){
                gameState[i] = 2;
                buttons[i].setText("");
            }
        }

}
