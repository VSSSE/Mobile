package org.dhbw.se.movietunes;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

public class LookUpSoundtrackActivity extends MainActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    EditText textField;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View searchButton;
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        setContentView(R.layout.look_up_soundtrack_activity);
        View welcomeText = findViewById(R.id.welcome);
        searchButton = findViewById(R.id.search_button);
        View introText = findViewById(R.id.intro_text);
        textField = (EditText) findViewById(R.id.text_input);
    }

    public void onClickLookUpSoundtracks(View v) {
        String movieTitle = textField.getText().toString();
        // searchResultSoundtrack=new Movie(movieTitle);
        // searchResultSoundtrack.setSoundtracks(lookUpSoundtrack(movieTitle));
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, movieTitle);
        startActivity(intent);
    }




}









