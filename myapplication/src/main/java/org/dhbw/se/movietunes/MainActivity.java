package org.dhbw.se.movietunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";


    //Connector
    //ParseObject: movieTitle-soundtrack

     EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        View searchButton;


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        View welcomeText = findViewById(R.id.welcome);
        searchButton = findViewById(R.id.search_button);
        View introText = findViewById(R.id.intro_text);
         textField = (EditText) findViewById(R.id.text_input);
        searchButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        String movieTitle = textField.getText().toString();
        // searchResultSoundtrack=new Movie(movieTitle);
        // searchResultSoundtrack.setSoundtracks(lookUpSoundtrack(movieTitle));


        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, movieTitle);
        startActivity(intent);
    }


}













