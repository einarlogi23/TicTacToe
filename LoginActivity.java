package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This Activity deals with the Login screen of the application
 **/
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    User LoginUser,user;
    UserList UserList;


    EditText Username;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button BackToMenu = (Button) findViewById(R.id.backbutton);
        Button LoginButton = (Button) findViewById(R.id.loginbutton);
        Button SignUpButton = (Button) findViewById(R.id.signupbutton);

        Username = (EditText) findViewById(R.id.UsernameEntry);
        Password = (EditText) findViewById(R.id.PasswordEntry);

        BackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebuttonclick(v);
            }
        });
        SignUpButton.setOnClickListener(new View.OnClickListener() {
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
        UserList.writeUserstoFile();

    }

    public void handlebuttonclick(View v) {

        switch (v.getId()) {
            case R.id.backbutton:
                Intent Menu = new Intent(this, MainActivity.class);
                Menu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(Menu);
                break;
            case R.id.loginbutton:
                user = new User();
                LoginUser = new User();
                String newUsername = Username.getText().toString();
                String newPassword = Password.getText().toString();
                LoginUser.setPassword(newPassword);
                LoginUser.setUsername(newUsername);
                try{
                    user=UserList.GetUser(UserList.ifUserExists(LoginUser));
                }
                catch(ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                 }
                catch(NullPointerException e){
                    e.printStackTrace();
                }

                if (newPassword.equals(user.getPassword())) {
                    MainActivity.user.flagloggedin(true);
                    user.flagloggedin(true);
                    UserList.setLoggedInUser(user);

                    Toast LoginToast = Toast.makeText(getApplicationContext(),"Welcome " + user.getUsername(), Toast.LENGTH_SHORT);
                    LoginToast.show();

                    Intent Main = new Intent(this, MainActivity.class);
                    Main.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(Main);
                }
                else {
                    Toast error = Toast.makeText(getApplicationContext(), "Login Failed :( Please Try Again ", Toast.LENGTH_SHORT);
                    error.show();
                }
                break;
            case R.id.signupbutton:
                Intent SignUp = new Intent(this, CreateAccountActivity.class);
                SignUp.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(SignUp);
                break;
        }


    }
}
