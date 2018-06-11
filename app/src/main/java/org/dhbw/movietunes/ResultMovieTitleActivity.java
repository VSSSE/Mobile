package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.controller.SearchMovieTitleController;

public class ResultMovieTitleActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.MovieTitleFor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_titles_activity);

    Intent intent = getIntent();
    TextView song = findViewById(R.id.song);

    String songTitle = intent.getStringExtra(EXTRA_MESSAGE);
    song.setText(songTitle);

    SearchMovieTitleController controller = new SearchMovieTitleController(this);
    controller.execute(songTitle);
  }

}
