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
import android.widget.ListView;
import android.widget.PopupMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dhbw.movietunes.controller.SearchSimilarSongsController;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class ResultSimilarSongsActivity extends AppCompatActivity  {

  private static final Logger LOGGER = Logger.getLogger(ResultSimilarSongsActivity.class.getName());

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.SimilarSongsFor";

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_similar_songs_activity);

    Intent intent = getIntent();
    ListView similarList = findViewById(R.id.similarSongs_list_view);


    String trackId = intent.getStringExtra(EXTRA_MESSAGE);
    SearchSimilarSongsController controller
            = new SearchSimilarSongsController(this);

    controller.execute(trackId);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }


}
