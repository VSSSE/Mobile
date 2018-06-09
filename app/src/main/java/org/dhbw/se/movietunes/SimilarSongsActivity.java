package org.dhbw.se.movietunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.se.movietunes.controller.SearchSimilarSongsController;
import org.dhbw.se.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/21/2017.
 */

public class SimilarSongsActivity extends AppCompatActivity {

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.similar_songs);

    Intent intent = getIntent();
    ListView similarSongsView = findViewById(R.id.similarSongs_list_view);

    String trackId = intent.getStringExtra("TRACK_ID");
    SearchSimilarSongsController controller
            = new SearchSimilarSongsController();

    List<String> similars = new ArrayList<>();
    for (Song song : controller.findSimilarSongs(trackId)) {
      similars.add(song.getSongTitle() + "Duration: " + song.getDuration()
              + ", " + song.getSinger());
    }

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, similars);
    similarSongsView.setAdapter(adapter);

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }
}
