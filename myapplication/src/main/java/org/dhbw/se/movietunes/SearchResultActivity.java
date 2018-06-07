package org.dhbw.se.movietunes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import org.dhbw.se.movietunes.controller.SearchByTitleController;
import org.dhbw.se.movietunes.model.Song;


public class SearchResultActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

  ListView resultList;
  SoundtrackSearchResult strackSearchResult;
  List<Song> currentSongList = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_search_result);

    Intent intent = getIntent();
    TextView movie = findViewById(R.id.movie);
    resultList = findViewById(R.id.soundtrack_list_view);

    String movieTitle = intent.getStringExtra(LookUpSoundtrackActivity.EXTRA_MESSAGE);
    movie.setText(movieTitle);

    SearchByTitleController controller = new SearchByTitleController();
    strackSearchResult = controller.searchTracklist(movieTitle);
    currentSongList = new ArrayList<>(strackSearchResult.getSongs());
    String[] strings = new String[currentSongList.size()];

    for (int i = 0; i < currentSongList.size(); i++) {
      Song song = currentSongList.get(i);
      strings[i] = song.getSongTitle() + " (Duration:" + song.getDuration() + ")"
              + song.getSinger();
    }

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
    resultList.setAdapter(adapter);
    resultList.setOnItemClickListener(this);

  }

  public PopupMenu showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.popup_menu, popup.getMenu());
    return popup;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

    final String trackId = currentSongList.get(position).getTrackId();

    //Todo Buttons to play on Spotify or on Youtube
    PopupMenu popupMenu = showPopup(resultList.getChildAt(position));
    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
      public boolean onMenuItemClick(MenuItem item) {
        String title = (String) item.getTitle();
        if (title.contains("Spotify")) {
          String url = strackSearchResult.getUrl();
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        } else if (title.contains("Youtube")) {
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=t7jzmW9tYX0")));

        } else if (title.contains("Facebook")) {
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.de")));

        } else if (title.contains("similar")) {
          Intent intent = new Intent(getApplicationContext(), SimilarSongsActivity.class);
          intent.putExtra("TRACK_ID", trackId);
          startActivity(intent);
        }
        return true;
      }
    });

    popupMenu.show();
  }
}


