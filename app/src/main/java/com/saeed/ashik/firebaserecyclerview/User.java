package com.saeed.ashik.firebaserecyclerview;

/**
 * Created by supto on 29/07/16.
 */
public class User {
    private String userName;
    private String Roll;

    public User() {
    }

    public User(String roll, String userName) {
        Roll = roll;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String roll) {
        Roll = roll;
    }
}
