package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());
  private Button lookUpSoundtrackButton;
  private Button lookUpMoviesButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_activity);

    lookUpSoundtrackButton = findViewById(R.id.search_button);
    lookUpMoviesButton = findViewById(R.id.movies_button);

    lookUpSoundtrackButton.setOnClickListener(this);
    lookUpMoviesButton.setOnClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent;
    switch (item.getItemId()) {
      case R.id.menu_search_by_movie:
        intent = new Intent(getApplicationContext(), SearchMovieTitlesActivity.class);
        startActivity(intent);
        break;

      case R.id.menu_search_by_song:
        intent = new Intent(getApplicationContext(), SearchMovieSoundtracksActivity.class);
        startActivity(intent);
        break;

      default:
        LOGGER.log(Level.INFO, "Option not found!");
        break;
    }

    return true;
  }

  @Override
  public void onClick(View v) {
    Button button = (Button) v;
    Intent intent;

    switch (button.getId()) {
      case R.id.search_button:
        intent = new Intent(getApplicationContext(), SearchMovieSoundtracksActivity.class);
        startActivity(intent);
        break;

      case R.id.movies_button:
        intent = new Intent(getApplicationContext(), SearchMovieTitlesActivity.class);
        startActivity(intent);
        break;
      default:
        LOGGER.log(Level.INFO, "Button not found!");
        break;
    }
  }
}













