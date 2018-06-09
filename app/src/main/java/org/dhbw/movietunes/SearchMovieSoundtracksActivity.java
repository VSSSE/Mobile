package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;

public class SearchMovieSoundtracksActivity extends AppCompatActivity implements View.OnClickListener {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.LookUpSoundtrack";

  EditText textField;
  Button searchButton;
  TextView welcomeText;
  TextView introText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.search_movie_soundtracks_activity);

    welcomeText = findViewById(R.id.welcome);
    searchButton = findViewById(R.id.search_button);
    introText = findViewById(R.id.intro_text);
    textField = findViewById(R.id.text_input);

    searchButton.setOnClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public void onClick(View v) {
    String movieTitle = textField.getText().toString();

    Intent intent = new Intent(getApplicationContext(), ResultMovieSoundtracksActivity.class);
    intent.putExtra(EXTRA_MESSAGE, movieTitle);
    startActivity(intent);
  }
}









