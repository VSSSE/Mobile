package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    View welcomeText, introText;
    EditText textField;
    View searchButton;
    String movieTitle;
    MovieSoundtrack searchResultSoundtrack;


   //Connector
    //ParseObject: movieTitle-soundtrack

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeText=findViewById(R.id.welcome);
        searchButton =  findViewById(R.id.search_button);
        introText=findViewById(R.id.intro_text);
        textField=(EditText) findViewById(R.id.text_input);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                movieTitle = textField.getText().toString();
               // searchResultSoundtrack=new MovieSoundtrack(movieTitle);
               // searchResultSoundtrack.setSoundtracks(lookUpSoundtrack(movieTitle));
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                intent.putExtra(EXTRA_MESSAGE, movieTitle);

                startActivity(intent);


            }
        });
}


}













