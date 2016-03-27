package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This Activity deals with the Scoreboard for players to see Top3 players of the game
 **/
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreBoardActivity extends AppCompatActivity {

    User user,Top1,Top2,Top3;
    UserList UserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button BackButton = (Button)findViewById(R.id.backbutton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });

    }

    public void handlebuttonclick(View v) {

        switch (v.getId()) {
            case R.id.backbutton:
                Intent BackToMenu = new Intent(this, MainActivity.class);
                BackToMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(BackToMenu);
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        UserList.writeUserstoFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserList = UserList.getUserList(getApplicationContext());

        //displaying Top 1-3 players to the Scoreboard
        TextView Top1Name = (TextView)findViewById(R.id.Top1Name);
        TextView Top2Name = (TextView)findViewById(R.id.Top2Name);
        TextView Top3Name = (TextView)findViewById(R.id.Top3Name);
        TextView Top1ScoreRatio = (TextView)findViewById(R.id.Top1ScoreRatio);
        TextView Top2ScoreRatio = (TextView)findViewById(R.id.Top2ScoreRatio);
        TextView Top3ScoreRatio = (TextView)findViewById(R.id.Top3ScoreRatio);

        user = new User();
        Top1 = new User();
        Top2 = new User();
        Top3 = new User();

        for(int i = 0;i<UserList.getsizeoflist();i++){
            user =UserList.GetUser(i);

            if(user.getWins()>=Top1.getWins()){
                Top1 = user;
            }

        }

        for(int i = 0;i<UserList.getsizeoflist();i++){
            user =UserList.GetUser(i);

            if(user.getWins()>=Top2.getWins() && user !=Top1){
                Top2 = user;
            }
        }

        for(int i = 0;i<UserList.getsizeoflist();i++){
            user =UserList.GetUser(i);

            if(user.getWins()>=Top3.getWins() && user != Top1 && user != Top2){
                Top3 = user;
            }

        }



        Top1Name.setText(Top1.getUsername());
        Top1ScoreRatio.setText(Top1.getWins()+"");

        Top2Name.setText(Top2.getUsername());
        Top2ScoreRatio.setText(Top2.getWins()+"");

        Top3Name.setText(Top3.getUsername());
        Top3ScoreRatio.setText(Top3.getWins()+"");
    }
}
