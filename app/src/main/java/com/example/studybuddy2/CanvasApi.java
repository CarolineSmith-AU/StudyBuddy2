package com.example.studybuddy2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CanvasApi {

    //@GET ()
    //Call<List<UserInfo>> getUserInfo();

    @FormUrlEncoded
        @POST ("/login/oauth2/token") //URL where code will be sent
        Call<String> sendCode(
                @Field("client_id") String client_id,
                @Field("client_secret") String client_secret,
                @Field("redirect_uri") String redirect_uri,
                @Field("code") String code
        );
}
