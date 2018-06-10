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

public class SearchMovieTitlesActivity extends MainMenu implements View.OnClickListener {


  private EditText textField;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.search_movie_titles_activity);

    textField = findViewById(R.id.text_input);
    Button lookupMoviesBtn = findViewById(R.id.search_button);

    lookupMoviesBtn.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    String songTitle = textField.getText().toString();

    Intent intent = new Intent(getApplicationContext(), ResultMovieTitleActivity.class);
    intent.putExtra(ResultMovieTitleActivity.EXTRA_MESSAGE, songTitle);
    startActivity(intent);
  }
}









