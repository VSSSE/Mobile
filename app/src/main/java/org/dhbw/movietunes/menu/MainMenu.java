package org.dhbw.movietunes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.SearchMovieSoundtracksActivity;
import org.dhbw.movietunes.SearchMovieTitlesActivity;

public class MainMenu extends AppCompatActivity {

  private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

      case android.R.id.home:
        finish();
        break;

      default:
        LOGGER.log(Level.INFO, "Option not found!");
        return false;
    }

    return true;
  }
}













