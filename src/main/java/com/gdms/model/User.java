package com.gdms.model;

public class User {
    private String username;
    private String password;
    private int identity;

    public User(String username, String password, int identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity=" + identity +
                '}';
    }
}
