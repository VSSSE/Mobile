package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;

public class ResultMovieSoundtracksActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.MovieSoundtracksFor";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_soundtracks_activity);

    Intent intent = getIntent();
    TextView movie = findViewById(R.id.movie);

    String movieTitle = intent.getStringExtra(EXTRA_MESSAGE);
    movie.setText(movieTitle);

    SearchMovieSoundtracksController controller = new SearchMovieSoundtracksController(this);
    controller.execute(movieTitle);

  }

}


