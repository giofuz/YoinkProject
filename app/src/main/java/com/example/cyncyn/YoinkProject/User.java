package com.example.cyncyn.YoinkProject;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 06/02/16.
 */
public class User {
    private int mId;
    private String mUsername;
    private String mPassword;

    public User(int id, String username, String password) {
        mId = id;
        mUsername = username;
        mPassword = password;
    }

    public User(String username, String password) {
        this(-1, username, password);
    }

    public int getId() { return mId; }
    public String getUsername() { return mUsername; }
    public String getPassword() { return mPassword; }

    public void setId(int id) { mId = id; }
    public void setUsername(String username) { mUsername = username; }
    public void setmPassword(String password) { mPassword = password; }
}
