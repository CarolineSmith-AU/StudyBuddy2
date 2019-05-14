package com.example.studybuddy2;

import android.support.design.widget.TextInputLayout;
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
import android.widget.Switch;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private String selectedSchool;
    private String authenticationURL;
    private Button confirmSchoolButton;
    private static final String successURL = "/login/oauth2/auth?code=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /* << -------------------- set up toolbar -------------------- >> */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Select School");



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
                WebView web = new WebView(LoginActivity.this);
                setContentView(web);
                web.loadUrl(authenticationURL);

                web.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.contains(successURL)) {
                            String requestCode = ;
                        }
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
            }
        });
    }
}
