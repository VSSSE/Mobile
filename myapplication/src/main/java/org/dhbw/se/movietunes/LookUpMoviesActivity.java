package org.dhbw.se.movietunes;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

public class LookUpMoviesActivity extends MainActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    EditText textField;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        View searchButton;


        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        Intent intent=getIntent();
        setContentView(R.layout.look_up_movies_activity);
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

// TODO MovieSearchResultActivity!!
        //Intent intent = new Intent(getApplicationContext(), MovieSearchResultActivity.class);
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, movieTitle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }



}









