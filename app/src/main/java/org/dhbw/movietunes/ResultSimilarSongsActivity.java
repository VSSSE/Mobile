package org.dhbw.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.controller.SearchSimilarSongsController;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class ResultSimilarSongsActivity extends AppCompatActivity {

  private List<String> similars;

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_similar_songs_activity);

    Intent intent = getIntent();

    String trackId = intent.getStringExtra("TRACK_ID");
    SearchSimilarSongsController controller
            = new SearchSimilarSongsController(this);

    controller.execute(trackId);

    similars = new ArrayList<>();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  public List<String> getSimilars() {
    return similars;
  }

  public void setSimilars(List<String> similars) {
    this.similars = similars;
  }

}
