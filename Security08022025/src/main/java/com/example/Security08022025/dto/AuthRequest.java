package com.example.Security08022025.dto;

public class AuthRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    public AuthRequest(String username, String passWord) {
        this.username = username;
        this.password = passWord;
    }

    public AuthRequest() {
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "username='" + username + '\'' +
                ", passWord='" + password + '\'' +
                '}';
    }
}
