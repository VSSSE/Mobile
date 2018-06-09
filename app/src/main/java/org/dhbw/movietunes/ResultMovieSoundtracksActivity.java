package org.dhbw.movietunes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.list.SoundtrackSearchResult;

public class ResultMovieSoundtracksActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

  ListView resultList;
  SoundtrackSearchResult strackSearchResult;
  List<Song> currentSongList = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_soundtracks_activity);

    Intent intent = getIntent();
    TextView movie = findViewById(R.id.movie);
    resultList = findViewById(R.id.soundtrack_list_view);

    String movieTitle = intent.getStringExtra(SearchMovieSoundtracksActivity.EXTRA_MESSAGE);
    movie.setText(movieTitle);

    SearchMovieSoundtracksController controller = new SearchMovieSoundtracksController();
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
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
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
          //TODO: get real link
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=t7jzmW9tYX0")));

        } else if (title.contains("Facebook")) {
          //TODO: open facebook app
          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.de")));

        } else if (title.contains("similar")) {
          Intent intent = new Intent(getApplicationContext(), ResultSimilarSongsActivity.class);
          intent.putExtra("TRACK_ID", trackId);
          startActivity(intent);
        }
        return true;
      }
    });

    popupMenu.show();
  }
}


