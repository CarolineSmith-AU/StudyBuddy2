package com.example.studybuddy2;

public class ResponseAccessToken {

    private String access_token;
    private String token_type;
    private User userInfo;
    private String refresh_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
