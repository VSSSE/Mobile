package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.logging.Logger;

public class SearchMovieSoundtracksActivity extends AppCompatActivity implements View.OnClickListener {

  private static final Logger LOGGER = Logger.getLogger(SearchMovieSoundtracksActivity.class.getName());


  private EditText textField;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.search_movie_soundtracks_activity);

    Button searchButton = findViewById(R.id.search_button);
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
    intent.putExtra(ResultMovieSoundtracksActivity.EXTRA_MESSAGE, movieTitle);
    startActivity(intent);
  }
}









