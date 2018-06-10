package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultMovieTitleActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.MovieTitleFor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_titles_activity);

    Intent intent = getIntent();
    String songTitle = intent.getStringExtra(EXTRA_MESSAGE);
    TextView song = findViewById(R.id.song);
    song.setText(songTitle);
  }

}
