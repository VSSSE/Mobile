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
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class LookUpSoundtrackActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    EditText textField;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        View searchButton;


        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
Intent intent=getIntent();
        setContentView(R.layout.look_up_soundtrack_activity);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.menu_search_by_movie:
                Intent intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Search by movie selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.menu_search_by_song:
                Toast.makeText(this, "Search by song ", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }


}









