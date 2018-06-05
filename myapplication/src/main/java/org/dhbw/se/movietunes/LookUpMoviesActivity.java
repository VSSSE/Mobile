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

public class LookUpMoviesActivity extends MainActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    EditText textField;
    View introText, welcomeText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View searchButton=findViewById(R.id.search_button);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_up_movies_activity);
        welcomeText = findViewById(R.id.welcome);
        introText = findViewById(R.id.intro_text);
        textField = (EditText) findViewById(R.id.text_input);
    }

    public void onClickLookUpMovies(View v) {
        String songTitle = textField.getText().toString();

// TODO MovieSearchResultActivity!!
        Intent intent = new Intent(getApplicationContext(), MovieSearchResultActivity.class);
       // Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, songTitle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }



}









