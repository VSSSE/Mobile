package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import com.example.myapplication.R;

public class MovieSearchResultActivity extends AppCompatActivity {
  TextView song;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.movie_search_result);

    Intent intent = getIntent();
    String songTitle = intent.getStringExtra(LookUpMoviesActivity.EXTRA_MESSAGE);
    song = findViewById(R.id.song);
    song.setText(songTitle);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }
}
