package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This Activity is the MainActivity and could be refered to as Main Menu
 **/
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button LoginButton;
    Button ScoreButton;
    Button PlayButton;
    UserList UserList;
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         LoginButton = (Button)findViewById(R.id.loginbutton);
         ScoreButton = (Button)findViewById(R.id.scoreboardbutton);
         PlayButton = (Button) findViewById(R.id.playbutton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });
        ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });


        displayPlayButton(false);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserList = UserList.getUserList(getApplicationContext());
        user = UserList.getLoggedInUser();
        if(user.getLoggedInUser()){
            displayPlayButton(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        UserList.writeUserstoFile();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handlebuttonclick(View v) {
        switch (v.getId()) {
            case R.id.loginbutton:
                Intent login = new Intent(this, LoginActivity.class);
                login.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(login);
                break;
            case R.id.scoreboardbutton:
                Intent scoreboard = new Intent(this, ScoreBoardActivity.class);
                scoreboard.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(scoreboard);
                break;
            case R.id.playbutton:
                Intent play = new Intent(this, GameActivity.class);
                play.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(play);
                 break;
        }
    }

    /**
     * method to display Playbutton if User is logged in
     * @param b boolean to determine if user is logged in or not
     */
    public void displayPlayButton(boolean b){
        if (b==false){
            PlayButton.setVisibility(View.GONE);
        }
        if(b==true){
            PlayButton.setVisibility(View.VISIBLE);
        }

    }


}
