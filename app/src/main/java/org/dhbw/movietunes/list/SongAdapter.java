package org.dhbw.movietunes.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.ImageLoader;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.player.SpotifyPlayer;
import org.dhbw.movietunes.player.YoutubePlayer;
import org.dhbw.movietunes.utils.Utils;

public class SongAdapter extends BaseAdapter {

  private static LayoutInflater inflater = null;
  public ImageLoader imageLoader;
  private Activity activity;
  private boolean activityType; //1 = ResultSimilarSongsActivity || 0 = ResultMovieSoundtracksActivity

  public SongAdapter(Activity activity) {
    this.activity = activity;
    inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageLoader = new ImageLoader(this.activity.getApplicationContext());

    if (this.activity.getClass() == ResultSimilarSongsActivity.class) {
      activityType = true;
    } else if (this.activity.getClass() == ResultMovieSoundtracksActivity.class) {
      activityType = false;
    }
  }

  public int getCount() {
    SQLiteDatabase db = Database.getDB(activity);

    String queryString = "Select count(S." + Song._ID + ") as anzahl"
                   + " FROM " + Song._TabellenName + " as S,";

    String[] args;

    if (activityType) {
      queryString += IsSimilarTo._TabellenName + " as I"
          + " WHERE S." + Song._TrackId + " = I." + IsSimilarTo._ToId
          + " AND I." + IsSimilarTo._IsId + " = ?";

      args = new String[]{ ((ResultSimilarSongsActivity)activity).getTrackId()};

    } else {
      queryString += IsPlayedIn._TabellenName + " as I"
              + " WHERE S." + Song._TrackId + " = I." + IsPlayedIn._SongId
              + " AND I." + IsPlayedIn._MovieName + " = ?";

      args = new String[]{ ((ResultMovieSoundtracksActivity)activity).getMovieTitle()};

    }

    Cursor cursor = db.rawQuery(queryString, args);

    cursor.moveToFirst();

    return cursor.getInt(cursor.getColumnIndexOrThrow("anzahl"));
  }

  public Song getItem(int position) {
    SQLiteDatabase db = Database.getDB(activity);

    Cursor cursor = db.rawQuery("SELECT " + "*"
            + " FROM " + Song._TabellenName
            + " WHERE " + Song._ID + " = " + position
            + ";", null);

    cursor.moveToFirst();

    return new Song(cursor);
  }

  public long getItemId(int position) {
    SQLiteDatabase db = Database.getDB(activity);

    String queryString = "Select S." + Song._ID
            + " FROM " + Song._TabellenName + " as S,";

    String[] args;

    if (activityType) {
      queryString += IsSimilarTo._TabellenName + " as I"
              + " WHERE S." + Song._TrackId + " = I." + IsSimilarTo._ToId
              + " AND I." + IsSimilarTo._IsId + " = ?";

      args = new String[]{ ((ResultSimilarSongsActivity)activity).getTrackId()};

    } else {
      queryString += IsPlayedIn._TabellenName + " as I"
              + " WHERE S." + Song._TrackId + " = I." + IsPlayedIn._SongId
              + " AND I." + IsPlayedIn._MovieName + " = ?";

      args = new String[]{ ((ResultMovieSoundtracksActivity)activity).getMovieTitle()};

    }

    Cursor cursor = db.rawQuery(queryString, args);

    cursor.moveToPosition(position);

    return cursor.getLong(cursor.getColumnIndexOrThrow(Song._ID));
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null)
      vi = inflater.inflate(R.layout.list_row_song, null);

    TextView title = vi.findViewById(R.id.title); // title
    TextView artist = vi.findViewById(R.id.artist); // artist name
    TextView duration = vi.findViewById(R.id.duration); // duration
    ImageView thumb_image = vi.findViewById(R.id.list_image); // thumb image

    final Song song = getItem((int) getItemId(position));

    // Setting all values in listview
    title.setText(song.getSongTitle());
    artist.setText(song.getArtist());
    duration.setText(song.getDuration());
    imageLoader.displayImage(song.getImageUri(), thumb_image);

    vi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        PopupMenu popupMenu = new PopupMenu(activity, v);
        MenuInflater inflater = popupMenu.getMenuInflater();

        if (activityType) {
          inflater.inflate(R.menu.popup_menu_similar_songs, popupMenu.getMenu());
        } else {
          inflater.inflate(R.menu.popup_menu_movie_soundtracks, popupMenu.getMenu());
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.spotify:
                SpotifyPlayer sp = new SpotifyPlayer(activity, song.getUri());
                sp.execute(song.getSongTitle() + " " + song.getArtist());
                break;
              case R.id.youTube:
                YoutubePlayer yt = new YoutubePlayer(activity);
                yt.execute(song.getSongTitle() + " " + song.getArtist());
                break;
              case R.id.similar:
                Intent intent = new Intent(activity.getApplicationContext(), ResultSimilarSongsActivity.class);
                intent.putExtra(ResultSimilarSongsActivity.EXTRA_MESSAGE, song.getTrackId());
                activity.startActivity(intent);
                break;
              case R.id.facebook:
                Utils.ShareText(activity, "I found " + song.getSongTitle()
                        + " with Movie Tunes! Listen to it on Spotify: "
                        + song.getUri() + " And visit the Movie Tunes Project: https://vssse.wordpress.com/");
                break;
              default:
                return false;
            }
            return true;
          }
        });

        popupMenu.show();
      }
    });
    return vi;
  }

}