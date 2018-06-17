package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.controller.SearchMovieTitleController;
import org.dhbw.movietunes.list.MovieAdapter;
import org.dhbw.movietunes.menu.MainMenu;
import org.dhbw.movietunes.model.Movie;

public class ResultMovieTitleActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.MovieTitleFor";
  private String songTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_titles_activity);

    Intent intent = getIntent();
    TextView song = findViewById(R.id.song);
    ListView list = findViewById(R.id.movietitles_list_view);
    songTitle = intent.getStringExtra(EXTRA_MESSAGE);
    song.setText(songTitle);

    MovieAdapter adapter = new MovieAdapter(this);
    list.setAdapter(adapter);

    SearchMovieTitleController controller = new SearchMovieTitleController(this);
    controller.execute(songTitle);
  }

  public String getSongTitle() {
    return songTitle;
  }
}
