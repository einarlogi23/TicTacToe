package com.example.einarlogi.tictactoe;
/**
 * Author Einar Logi Einarsson
 * 03/20/2016
 * TicTacToe App for CSCI343 with  Dr.Clint Fuchs
 * This class Holds a single User object that will be playing the game
 **/
public class User {
    String username;
    String password;
    int totalWins;
    int totalLosses;
    int totalTies;
    boolean userloggedin;

    /**
     * Constructor for a User
     */
    public User() {
        username = "";
        password = "";
        totalWins = 0;
        totalLosses = 0;
        totalTies = 0;
        userloggedin = false;

    }

    /**
     * method to set the Username of user
     * @param newName the username to be set
     */
    public void setUsername(String newName) {
        username = newName;

    }

    /**
     * method to set the password of a user
     * @param pw password to be set
     */
    public void setPassword(String pw) {
        password = pw;
    }

    /**
     * Method to add a single win to users record
     */
    public void addWin() {
        totalWins++;
    }

    /**
     * Method to add a single loss to users record
     */
    public void addLoss() {
        totalLosses++;
    }

    /**
     * Method to add a single tie to a users record
     */
    public void addTie() {
        totalTies++;
    }

    /**
     * method to set users record of wins
     * @param wins total number of wins to set
     */
    public void setWins(int wins) {
        totalWins = wins;
    }

    /**
     * method to set the users record of losses
     * @param losses total numbers of losses to set
     */
    public void setLosses(int losses) {
        totalLosses = losses;
    }

    /**
     * method to set the users record of ties
     * @param ties total numbers of ties to set
     */
    public void setTies(int ties) {
        totalTies = ties;
    }

    /**
     * method to get total wins of user
     * @return total number of wins
     */
    public int getWins() {
        return totalWins;
    }

    /**
     * method to get total losses of user
     * @return total number of losses
     */
    public int getLosses() {
        return totalLosses;
    }

    /**
     * method to get the total ties of user
     * @return total number of ties
     */
    public int getTies() {
        return totalTies;
    }

    /**
     * method to get the Username of User
     * @return the string including the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * method to get the passowrd of User
     * @return the string including the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * method to flag if user logged in
     * @param bool
     */
    public void flagloggedin(boolean bool) {
        userloggedin = true;
    }

    /**
     * method to see if user is logged in or not
     * @return boolean determining if user is logged in
     */
    public boolean getLoggedInUser() {
        return userloggedin;

    }

}



