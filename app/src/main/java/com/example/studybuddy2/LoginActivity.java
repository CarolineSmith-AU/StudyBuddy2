package com.example.studybuddy2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.instructure.canvasapi.utilities.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String PREFS = "My Preferences";
    private String selectedSchool;
    private String authenticationURL;
    private Button confirmSchoolButton;
    private static final String successURL = "/login/oauth2/auth?code=";
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private String requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        /* << -------------------- set up toolbar -------------------- >> */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Select School");



        /* << -------------------- set up SharedPreferences to save access token -------------------- >>*/
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        /* << -------------------- set up searchable spinner -------------------- >> */
        Spinner schoolSearchableSpinner = findViewById(R.id.schoolSearchableSpinner);
        //create ArrayAdapter using string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.canvas_auburn_university, android.R.layout.simple_spinner_item);
        //specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        schoolSearchableSpinner.setAdapter(adapter);

        schoolSearchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSchool = parent.getItemAtPosition(position).toString();

                switch (selectedSchool) {
                    /*UNCOMMENT WHEN YOU WANT TO IMPLEMENT FOR OTHER SCHOOLS
                    case "Alfred State College":
                        authenticationURL = "https://alfredu.instructure.com/login/oauth2/auth/";
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "American College of Education":
                        authenticationURL = "https://ace.instructure.com/login/oauth2/auth/";
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Arizona State University":
                        authenticationURL = "https://weblogin.asu.edu/cas/login/oauth2/auth/";
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                        */
                    case "Auburn University Main Campus":
                        authenticationURL = "https://auburn.instructure.com/login/oauth2/auth?client_id=XXX&response_type=code&redirect_uri=urn:ietf:wg:oauth:2.0:oob";
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /* <<-------------------- set up onClickListener() for Confirm Button -------------------->> */
        confirmSchoolButton = findViewById(R.id.confirmSchoolButton);
        confirmSchoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* -------------------- set up WebView for authenticationURL --------------------*/
                final WebView web = new WebView(LoginActivity.this);
                setUpWebView(web, authenticationURL);


                web.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.contains(successURL)) { //if (user has successfully logged in)
                            requestCode = url.substring(url.indexOf(successURL) + url.length());
                            return true;
                        } else {
                            setUpWebView(web, authenticationURL); //redirect back to authenticationURL => prompt another login attempt
                            return false;
                        }
                    }
                });
            }
        });
    }

    /* -------------------- use requestCode, client_id and client_secret to obtain final access token --------------------*/
    private void getAccessToken(Context context, String code) {
        LinkedList<NameValuePair> values = new LinkedList<>();
            values.add(new BasicNameValuePair("client_id", CLIENT_ID)); //index: 0
            values.add(new BasicNameValuePair("client_secret", CLIENT_SECRET)); //index: 1
            values.add(new BasicNameValuePair("response_type", "code")); //index: 2
            values.add(new BasicNameValuePair("redirect_uri", "urn:ietf:wg:oauth:2.0:oob")); //index: 3
            values.add(new BasicNameValuePair("code", code)); //index: 4

            //String result = sendPost(context, "/login/oauth2/token", values);
    }

    private void sendPost(Context context, String exchangeUri, LinkedList<NameValuePair> values) {

        /* << -------------------- use Retrofit object to set up base URL and to implement GSON converter-------------------- >>*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://auburn.instructure.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CanvasApi canvasApi = retrofit.create(CanvasApi.class); //retrofit adds own implementation
        Call<String> call = canvasApi.sendCode(values.get(0).getValue(), values.get(1).getValue(), values.get(3).getValue(), values.get(4).getValue());

        //run in background thread
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                SharedPreferences.Editor editor = sharedPreferences.edit();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //what to put here?
            }
        });
    }

    private void setUpWebView(WebView web, String authUrl) {
        setContentView(web);
        web.loadUrl(authenticationURL);
    }
}
