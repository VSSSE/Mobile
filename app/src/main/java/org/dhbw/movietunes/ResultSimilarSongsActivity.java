package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import org.dhbw.movietunes.controller.SearchSimilarSongsController;
import org.dhbw.movietunes.menu.MainMenu;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class ResultSimilarSongsActivity extends MainMenu {

  public static final String EXTRA_MESSAGE = "org.dhbw.movietunes.SimilarSongsFor";
  private String trackId;

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_similar_songs_activity);

    Intent intent = getIntent();

    trackId = intent.getStringExtra(EXTRA_MESSAGE);
    SearchSimilarSongsController controller
            = new SearchSimilarSongsController(this);

    controller.execute(trackId);

  }

  public String getTrackId() {
    return trackId;
  }
}
