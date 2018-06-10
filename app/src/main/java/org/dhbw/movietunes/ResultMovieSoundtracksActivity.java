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
import android.widget.TextView;
import java.util.List;
import org.dhbw.movietunes.controller.SearchMovieSoundtracksController;
import org.dhbw.movietunes.list.SoundtrackSearchResult;
import org.dhbw.movietunes.model.Song;

public class ResultMovieSoundtracksActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.result_movie_soundtracks_activity);

    Intent intent = getIntent();
    TextView movie = findViewById(R.id.movie);

    String movieTitle = intent.getStringExtra(SearchMovieSoundtracksActivity.EXTRA_MESSAGE);
    movie.setText(movieTitle);

    SearchMovieSoundtracksController controller = new SearchMovieSoundtracksController(this);
    controller.execute(movieTitle);

  }

  public PopupMenu showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.popup_menu_movie_soundtracks, popup.getMenu());
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
        switch (item.getItemId())
        {
          case R.id.spotify:
            String url = strackSearchResult.getUrl();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            break;

          case R.id.youTube:
            break;
          case R.id.similar:
            Intent intent = new Intent(getApplicationContext(), ResultSimilarSongsActivity.class);
            intent.putExtra("TRACK_ID", trackId);
            startActivity(intent);
            break;
          case R.id.facebook:
            String ShareBody = "I love Movie Tunes!";
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Song found on Movie tunes");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, ShareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            break;
        }
        return true;
      }
    });

    popupMenu.show();
  }

}


