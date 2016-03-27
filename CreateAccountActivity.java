package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This Activity deals with players creating a new account
 **/
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {
    private User newuser,existinguser;
    private UserList UserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button BackToMenu = (Button)findViewById(R.id.backbutton);
        Button SignupButton = (Button)findViewById(R.id.createaccountbutton);

        BackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserList = UserList.getUserList(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void handlebuttonclick(View v) {
        switch (v.getId()) {
            case R.id.backbutton:
                Intent toLogin = new Intent(this, LoginActivity.class);
                toLogin.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(toLogin);
                break;
            case R.id.createaccountbutton:
                TextView NewUsername = (TextView)findViewById(R.id.NewUsernameEdit);
                TextView NewPassword1 = (TextView)findViewById(R.id.NewPass1Edit);
                TextView NewPassword2 = (TextView)findViewById(R.id.NewPass2Edit);
                if(UserList != null) {
                    for (int i = 0; i < UserList.getsizeoflist(); i++) {
                        if (UserList.GetUser(i).getUsername().equals(NewUsername.getText().toString())) {
                            existinguser = new User();
                            existinguser = UserList.GetUser(i);
                            break;
                        }
                    }
                }
                if(NewPassword1.getText().toString().equals(NewPassword2.getText().toString()) && existinguser==null ){
                    newuser = new User();
                    newuser.setUsername(NewUsername.getText().toString());
                    newuser.setPassword(NewPassword1.getText().toString());
                    newuser.setWins(0);
                    newuser.setTies(0);
                    newuser.setLosses(0);

                    UserList.addUsertoList(newuser);

                    Toast success = Toast.makeText(getApplicationContext(), "Account Successfully created", Toast.LENGTH_SHORT);
                    success.show();

                    existinguser = null;
                    break;
                }

                else if(existinguser != null){
                    Toast error = Toast.makeText(getApplicationContext(),"Account already exists",Toast.LENGTH_SHORT);
                    error.show();
                    existinguser = null;
                    break;
                }

                else if(!(NewPassword1.getText().toString().equals(NewPassword2.getText().toString()))){
                    NewPassword1.setText("");
                    NewPassword2.setText("");

                    Toast error = Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT);
                    error.show();
                    existinguser = null;
                    break;
                }
        }
    }
}
