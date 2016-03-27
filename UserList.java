package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This class holds a List of Users that play the game
 **/
import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by EinarLogi on 29/02/16.
 */
public class UserList {

    private ArrayList<User> ListOfUsers;
    private static UserList UserList;
    static final String STORAGE_FILE = "userdata.dat";
    private Context context;
    User LoggedInUser;
    Scanner scan;
    File file;
    User tmpUser;

    /**
     * Singleton for UserList, Make sure that there only exists one UserList
     * @param con context of the activity in use
     * @return the UserList new UserList if does not exist, if exist returns the existing UserList
     */
    public static UserList getUserList(Context con){
        if(UserList==null){
            UserList =new UserList(con);
        }
        return UserList;
    }
    /**
     * Constructor for the UserList
     * @param con the context of the Activity to use the List
     */
    public UserList(Context con){
        ListOfUsers = new ArrayList<User>();
        context = con;
        LoggedInUser = new User();
        this.FillUserList();
    }

    /**
     * Method to Fill the UserList with all users by reading from File
     */
    public void FillUserList(){

        try {
            file = new File(context.getFilesDir() ,STORAGE_FILE);
            scan = new Scanner(file);
            while (scan.hasNext()) {
                tmpUser = new User();
                tmpUser.setUsername(scan.nextLine());
                tmpUser.setPassword(scan.nextLine());
                tmpUser.setWins(scan.nextInt());
                tmpUser.setLosses(scan.nextInt());
                tmpUser.setTies(scan.nextInt());
                scan.nextLine();
                ListOfUsers.add(tmpUser);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to add user to UserList
     * @param u User to add
     */
    public void addUsertoList(User u){
        ListOfUsers.add(u);
    }
    /**
     * Method to write all Users in arraylist to file
     */
    public void writeUserstoFile(){
        User user;
        PrintWriter writer;
        FileOutputStream fout;
        int i=0;
        try {
            fout = context.openFileOutput(STORAGE_FILE, Context.MODE_PRIVATE);
            writer = new PrintWriter(fout);
            while(i<this.getsizeoflist()) {
                user = this.GetUser(i);
                writer.println(user.getUsername());
                writer.println(user.getPassword());
                writer.println(user.getWins());
                writer.println(user.getLosses());
                writer.println(user.getTies());
                i++;
            }
            writer.flush();
            writer.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
    /**
     * Method to get size of list
     * @return size of arraylist
     */
    public int getsizeoflist(){
        return this.ListOfUsers.size();
    }

    public User GetUser(int i){
        return this.ListOfUsers.get(i);
    }
    /**
     * Method to check to see if User exists or not.
     * @param u User to check if exists
     * @return if existst return number in list, if not -1
     */
    public int ifUserExists(User u) {
        if (ListOfUsers.size() > 0) {
            for (int i = 0; i < ListOfUsers.size(); i++) {
                if (u.getUsername().equals(ListOfUsers.get(i).getUsername())) {
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * Method to get the user that is currently logged in
     * @return User object of the currently logged in user
     */
    public User getLoggedInUser(){
        return LoggedInUser;
    }

    /**
     * Method to set User as LoggedinUser
      * @param u
     */
    public void setLoggedInUser(User u){
        LoggedInUser=u;
    }
}




