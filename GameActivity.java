package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This Activity deals with playing the Game of Tic Tac Toe
 **/
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    boolean LetterX = true;
    boolean winner =false;
    private UserList UserList;
    int counter=0;
    String winningletter= new String("");
    Button Button11,Button12,Button13,Button21,Button22,Button23,Button31,Button32,Button33,ResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button BackButton = (Button)findViewById(R.id.backbutton);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });

        Button11 = (Button)findViewById(R.id.button11);
        Button12 = (Button)findViewById(R.id.button12);
        Button13 = (Button)findViewById(R.id.button13);
        Button21 = (Button)findViewById(R.id.button21);
        Button22 = (Button)findViewById(R.id.button22);
        Button23 = (Button) findViewById(R.id.button23);
        Button31 = (Button) findViewById(R.id.button31);
        Button32 = (Button) findViewById(R.id.button32);
        Button33 = (Button) findViewById(R.id.button33);
        ResetButton = (Button)findViewById(R.id.ResetButton);

        Button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });

        Button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });
        Button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });
        Button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
                computerTurn();
            }
        });
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamebuttonclick(v);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        UserList.writeUserstoFile();
    }

    private void handlebuttonclick(View v) {

        switch (v.getId()) {
            case R.id.backbutton:
                Intent BackToMenu = new Intent(this, MainActivity.class);
                BackToMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(BackToMenu);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        UserList = UserList.getUserList(getApplicationContext());
    }

    /**
     * Button handler for Gameplaybuttons
     * @param v
     */
    private void gamebuttonclick(View v) {

        switch (v.getId()) {
            case R.id.button11:
                playermove(Button11);
                break;
            case R.id.button12:
                playermove(Button12);
                break;
            case R.id.button13:
                playermove(Button13);
                break;
            case R.id.button21:
                playermove(Button21);
                break;
            case R.id.button22:
                playermove(Button22);
                break;
            case R.id.button23:
                playermove(Button23);
                break;
            case R.id.button31:
                playermove(Button31);
                break;
            case R.id.button32:
                playermove(Button32);
                break;
            case R.id.button33:
                playermove(Button33);
                break;
            case R.id.ResetButton:
                ResetGame();
                break;
        }

    }

    /**
     * method to set X and O to button clicked
     * @param clickedButton button that user clicked
     */
    public void playermove(Button clickedButton){
        counter++;

        if(LetterX){
            clickedButton.setText("X");
            clickedButton.setClickable(false);
            LetterX =false;
        }
        else if(!LetterX){
            clickedButton.setText("O");
            clickedButton.setClickable(false);
            LetterX = true;
        }
        if(counter>=5){
            if(checkforWinner()){
                Toast winnerToast = Toast.makeText(getApplicationContext(),"aaaaaand "+ winningletter + " is the WINNER !!!",Toast.LENGTH_SHORT);
                winnerToast.show();
                ForceNoClick();
                winner = false;

                if(winningletter.equals("X")){
                    UserList.getLoggedInUser().addWin();
                }
                if(winningletter.equals("O")){
                    UserList.getLoggedInUser().addLoss();
                }
            }
        }
        if(counter==9 && !winner){
            UserList.getLoggedInUser().addTie();
            Toast drawToast = Toast.makeText(getApplicationContext()," OHH we have a Draw :| ",Toast.LENGTH_SHORT);
            drawToast.show();
        }
    }
    /**
     * Method to Reset Game to playable again
     */
    public void ResetGame(){
        LetterX=true;
        counter = 0;
        Button11.setClickable(true);
        Button12.setClickable(true);
        Button13.setClickable(true);
        Button21.setClickable(true);
        Button22.setClickable(true);
        Button23.setClickable(true);
        Button31.setClickable(true);
        Button32.setClickable(true);
        Button33.setClickable(true);

        Button11.setText("#");
        Button12.setText("#");
        Button13.setText("#");
        Button21.setText("#");
        Button22.setText("#");
        Button23.setText("#");
        Button31.setText("#");
        Button32.setText("#");
        Button33.setText("#");
    }

    /**
     * method to check if there is a winner in the game
     * @return boolean determing if winner exists.
     */
    public boolean checkforWinner(){
         if(Button11.getText().equals(Button12.getText()) && Button11.getText().equals(Button13.getText()) && !Button11.getText().equals("#")){
             winningletter=Button11.getText().toString();
            return true;
         }
         if(Button21.getText().equals(Button22.getText()) && Button21.getText().equals(Button23.getText()) && !Button21.getText().equals("#")){
             winningletter=Button21.getText().toString();
            return true;
         }
         if(Button31.getText().equals(Button32.getText()) && Button31.getText().equals(Button33.getText()) && !Button31.getText().equals("#")){
             winningletter=Button31.getText().toString();
            return true;
         }
         if(Button11.getText().equals(Button21.getText()) && Button11.getText().equals(Button31.getText()) && !Button11.getText().equals("#")){
             winningletter=Button11.getText().toString();
            return true;
         }
         if(Button12.getText().equals(Button22.getText()) && Button12.getText().equals(Button32.getText()) && !Button12.getText().equals("#")){
             winningletter=Button12.getText().toString();
             return true;
         }
         if(Button13.getText().equals(Button23.getText()) && Button23.getText().equals(Button33.getText()) && !Button13.getText().equals("#")){
             winningletter=Button13.getText().toString();
             return true;
         }
        if(Button11.getText().equals(Button22.getText()) && Button22.getText().equals(Button33.getText()) && !Button11.getText().equals("#")){
            winningletter=Button11.getText().toString();
            return true;
        }
        if(Button31.getText().equals(Button22.getText()) && Button31.getText().equals(Button13.getText()) && !Button31.getText().equals("#")){
            winningletter=Button31.getText().toString();
            return true;
        }
        return false;
    }

    /**
     * Method to set all buttons to Not clickable at end of game
     */
    public void ForceNoClick(){
        Button11.setClickable(false);
        Button12.setClickable(false);
        Button13.setClickable(false);
        Button21.setClickable(false);
        Button22.setClickable(false);
        Button23.setClickable(false);
        Button31.setClickable(false);
        Button32.setClickable(false);
        Button33.setClickable(false);
    }

    /**
     * Method to initialize computers turn.
     */
    public void computerTurn(){
        if(Button11.isClickable()){
            Button11.setClickable(false);
            playermove(Button11);
        }
        else if(Button22.isClickable()){
            playermove(Button22);
        }
        else if(Button13.isClickable()){
            playermove(Button13);
        }
        else if(Button31.isClickable()){
            playermove(Button31);
        }
        else if(Button12.isClickable()){
            playermove(Button12);
        }
        else if(Button23.isClickable()){
            playermove(Button23);
        }
        else if(Button33.isClickable()){
            playermove(Button33);
        }
        else if(Button32.isClickable()){
            playermove(Button32);
        }
        else if(Button21.isClickable()){
            playermove(Button21);
        }
    }
}
