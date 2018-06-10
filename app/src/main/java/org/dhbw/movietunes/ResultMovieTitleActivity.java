package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class ResultMovieTitleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_titles_activity);

    Intent intent = getIntent();
    String songTitle = intent.getStringExtra(SearchMovieTitlesActivity.EXTRA_MESSAGE);
    TextView song = findViewById(R.id.song);
    song.setText(songTitle);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }
}
