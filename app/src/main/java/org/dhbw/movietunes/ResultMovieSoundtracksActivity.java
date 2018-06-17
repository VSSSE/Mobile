package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.menu.MainMenu;

public class ResultMovieSoundtracksActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.MovieSoundtracksFor";
  private String movieTitle;
  private SongAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_soundtracks_activity);

    Intent intent = getIntent();
    TextView movie = findViewById(R.id.movie);
    ListView list = findViewById(R.id.soundtrack_list_view);

    movieTitle = intent.getStringExtra(EXTRA_MESSAGE);
    movie.setText(movieTitle);

    adapter = new SongAdapter(this);
    list.setAdapter(adapter);

    if (adapter.getCount() <= 2) {
      SearchMovieSoundtracksController controller = new SearchMovieSoundtracksController(this);
      controller.execute(movieTitle);
    }

  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void updateList() {
    adapter.notifyDataSetChanged();
  }
}


