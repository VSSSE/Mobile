package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.controller.SearchSimilarSongsController;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.menu.MainMenu;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class ResultSimilarSongsActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.SimilarSongsFor";
  private String trackId;
  private SongAdapter adapter;

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_similar_songs_activity);

    Intent intent = getIntent();
    ListView list = findViewById(R.id.similarSongs_list_view);

    trackId = intent.getStringExtra(EXTRA_MESSAGE);

    adapter = new SongAdapter(this);
    list.setAdapter(adapter);


    if(adapter.getCount() <= 2) {
      SearchSimilarSongsController controller
              = new SearchSimilarSongsController(this);
      controller.execute(trackId);
    }

  }

  public String getTrackId() {
    return trackId;
  }

  public void updateList() {
    adapter.notifyDataSetChanged();
  }
}
