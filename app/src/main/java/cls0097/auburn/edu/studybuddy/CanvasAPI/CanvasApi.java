package cls0097.auburn.edu.studybuddy.CanvasAPI;

import java.util.List;

import cls0097.auburn.edu.studybuddy.JSONObjects.Course;
import cls0097.auburn.edu.studybuddy.JSONObjects.ResponseAccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CanvasApi {

    @FormUrlEncoded
        @POST ("/login/oauth2/token") //endpoint URL where code will be sent
        Call<ResponseAccessToken> sendCode(
                @Field("grant_type") String grant_type,
                @Field("client_id") String client_id,
                @Field("client_secret") String client_secret,
                @Field("redirect_uri") String redirect_uri,
                @Field("code") String code
        );

    //TODO: Look into using Authorization header instead of passing token as a parameter.
    @GET("/api/v1/courses")
    Call<List<Course>> getUserCourses(@Header("Authorization") String access_token);
}

