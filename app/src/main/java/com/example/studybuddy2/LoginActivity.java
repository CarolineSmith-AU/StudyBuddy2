package com.example.studybuddy2;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout text_input_school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_input_school = findViewById(R.id.text_input_school);

        //START: set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Select School");
        //END

        Spinner schoolSearchableSpinner = findViewById(R.id.schoolSearchableSpinner);
        //create ArrayAdapter using string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.canvas_schools, android.R.layout.simple_spinner_item);
        //specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        schoolSearchableSpinner.setAdapter(adapter);

        schoolSearchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSchool = parent.getItemAtPosition(position).toString();

                switch (selectedSchool) {
                    case "Alfred State College":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Ambra College":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "American College of Education":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Arizona State University":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Association of Independent Colleges of Art and Design":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Athabasca University":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    case "Auburn University Main Campus":
                        Toast.makeText(LoginActivity.this, selectedSchool + " selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        text_input_school.setError("Please select a school");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
