package cls0097.auburn.edu.studybuddy.JSONObjects;

public class ResponseAccessToken {

    private String access_token;
    private String token_type;
    private User userInfo;
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


    public int getExpires_in() {
        return expires_in;
    }
}
