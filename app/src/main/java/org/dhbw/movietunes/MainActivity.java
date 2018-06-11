package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dhbw.movietunes.menu.MainMenu;

public class MainActivity extends MainMenu implements View.OnClickListener {

  private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_activity);

    Button lookUpSoundtrackButton = findViewById(R.id.search_button);
    Button lookUpMoviesButton = findViewById(R.id.movies_button);

    lookUpSoundtrackButton.setOnClickListener(this);
    lookUpMoviesButton.setOnClickListener(this);
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













