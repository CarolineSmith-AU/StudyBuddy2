package cls0097.auburn.edu.studybuddy.Pages;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cls0097.auburn.edu.studybuddy.CanvasAPI.CanvasApi;
import cls0097.auburn.edu.studybuddy.Login.LoginActivity;
import com.example.studybuddy2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cls0097.auburn.edu.studybuddy.JSONObjects.Course;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {

    private CanvasApi canvasApi;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        canvasApi = setUpRetrofit().create(CanvasApi.class);
        sharedPreferences = getSharedPreferences(LoginActivity.PREFS, MODE_PRIVATE);



        /* << -------------------- set up toolbar -------------------- >> */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home Page");
    }

    private List<Course> getUserCourses() {
        Call<List<Course>> call = canvasApi.getUserCourses(sharedPreferences.getString("access_token", "INVALID TOKEN")); //sec. field is default token
        final List<Course> userCourses = new ArrayList<>();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (!response.isSuccessful()) {
                    //what to do here?
                }
                else {
                    for (Course course : userCourses) {
                        userCourses.add(course);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                //what to put here?
            }
        });
        return userCourses;
    }

    private Retrofit setUpRetrofit() {
        /* << -------------------- use Retrofit object to set up base URL and to implement GSON converter-------------------- >>*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://auburn.instructure.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
