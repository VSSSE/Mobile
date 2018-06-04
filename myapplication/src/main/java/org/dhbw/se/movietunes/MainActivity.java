package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity   {



    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";


    //Connector
    //ParseObject: movieTitle-soundtrack

     //EditText textField;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Button lookUpSoundtrackButton;
        Button lookUpMoviesButton;
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        setContentView(R.layout.activity_main);
        View welcomeText = findViewById(R.id.welcome);
        lookUpSoundtrackButton = findViewById(R.id.search_button);
        lookUpMoviesButton = findViewById(R.id.movies_button);
       // View introText = findViewById(R.id.intro_text);
         //textField = (EditText) findViewById(R.id.text_input);

    }

    public void onClickMainActivity(View v) {
        //String movieTitle = textField.getText().toString();
        // searchResultSoundtrack=new Movie(movieTitle);
        // searchResultSoundtrack.setSoundtracks(lookUpSoundtrack(movieTitle));
    Button button = (Button)v;
        String text= (String)button.getText();
        Intent intent;


        switch(button.getId()){
            case R.id.search_button:
                intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
                // intent.putExtra(EXTRA_MESSAGE, movieTitle);
                startActivity(intent);
                break;
            case R.id.movies_button:
                intent = new Intent(getApplicationContext(), LookUpMoviesActivity.class);
                startActivity(intent);
                break;


        }


        //Intent intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
       // intent.putExtra(EXTRA_MESSAGE, movieTitle);
        //startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.menu_search_by_movie:
                intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Search by movie selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.menu_search_by_song:
                intent = new Intent(getApplicationContext(), LookUpMoviesActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Search by song title ", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }



}













