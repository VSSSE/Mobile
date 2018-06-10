package org.dhbw.movietunes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;

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


