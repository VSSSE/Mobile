package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.dhbw.movietunes.R;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.list.SongAdapter;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieSoundtracksController extends AsyncSearchController {

  public SearchMovieSoundtracksController(ResultMovieSoundtracksActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    ArrayList<Song> result = new ArrayList(spotifyCommunication.findSoundtracks(searchString));


    SQLiteDatabase db = Database.getDB(activity);

    String[] args = new String[]{searchString};
    Cursor finder = db.rawQuery("Select " + Movie._MovieTitle
                    + " FROM " + Movie._TabellenName + " as M"
                    + " WHERE M." + Movie._MovieTitle + " = ?"
            , args);

    String movieName;
    if (finder.getCount() > 0) {
      finder.moveToFirst();
      movieName = new String(finder.getBlob(
              finder.getColumnIndexOrThrow(Song._TrackId)), UTF8_CHARSET);
    } else {
      //TODO search movie if not exist

      movieName = searchString;

      ContentValues valuesCreate = new ContentValues();

      valuesCreate.put(Movie._MovieTitle, movieName);

      synchronized (db) {
        db.insert(Movie._TabellenName, null, valuesCreate);
      }
    }


    for (Song song : result) {
      ContentValues values = new ContentValues();

      values.put(Song._Artist, song.getArtist());
      values.put(Song._Duration, song.getDuration());
      values.put(Song._ImageUri, song.getImageUri());
      values.put(Song._SongTitle, song.getSongTitle());
      values.put(Song._TrackId, song.getTrackId());
      values.put(Song._Uri, song.getUri());

      ContentValues valuesCon = new ContentValues();

      valuesCon.put(IsPlayedIn._SongId, song.getTrackId());
      valuesCon.put(IsPlayedIn._MovieName, movieName);


      synchronized (db) {
        db.insert(Song._TabellenName, null, values);
        db.insert(IsPlayedIn._TabellenName, null, valuesCon);
      }

    }

    return result.isEmpty() ? false : true;
  }

  @Override
  protected void postResult(Boolean result) {
    if(result) {
      ((ResultMovieSoundtracksActivity)activity).updateList();
    }
  }

}

