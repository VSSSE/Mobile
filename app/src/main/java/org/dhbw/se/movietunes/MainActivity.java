package org.dhbw.se.movietunes;

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

public class MainActivity extends AppCompatActivity {

  private static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());
  Button lookUpSoundtrackButton;
  Button lookUpMoviesButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    lookUpSoundtrackButton = findViewById(R.id.search_button);
    lookUpMoviesButton = findViewById(R.id.movies_button);
  }

  public void onClickMainActivity(View v) {
    Button button = (Button) v;
    Intent intent;

    switch (button.getId()) {
      case R.id.search_button:
        intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
        startActivity(intent);
        break;

      case R.id.movies_button:
        intent = new Intent(getApplicationContext(), LookUpMoviesActivity.class);
        startActivity(intent);
        break;
      default:
        LOGGER.log(Level.INFO, "Button not found!");
        break;
    }
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
        intent = new Intent(getApplicationContext(), LookUpMoviesActivity.class);
        startActivity(intent);
        break;

      case R.id.menu_search_by_song:
        intent = new Intent(getApplicationContext(), LookUpSoundtrackActivity.class);
        startActivity(intent);
        break;

      default:
        LOGGER.log(Level.INFO, "Option not found!");
        break;
    }

    return true;
  }

}













