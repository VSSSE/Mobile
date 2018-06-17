package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.dhbw.movietunes.ResultSimilarSongsActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchSimilarSongsController extends AsyncSearchController {

  public SearchSimilarSongsController(ResultSimilarSongsActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    SpotifyCommunication spotifyCommunication = new SpotifyCommunication();

    ArrayList<Song> result = new ArrayList(spotifyCommunication.getRecommendations(searchString));

    SQLiteDatabase db = Database.getDB(activity);

    String[] args = new String[]{searchString};
    Cursor finder = db.rawQuery("Select " + Song._TrackId
                    + " FROM " + Song._TabellenName + " as S"
                    + " WHERE S." + Song._SongTitle + " = ?"
            , args);


    if (finder.getCount() > 0) {
      finder.moveToFirst();
      String toIT = new String(finder.getBlob(
              finder.getColumnIndexOrThrow(Song._TrackId)), UTF8_CHARSET);

      for (Song song : result) {
        ContentValues values = new ContentValues();

        try {
          values.put(Song._Artist, song.getArtist().getBytes("UTF-8"));
          values.put(Song._Duration, song.getDuration().getBytes("UTF-8"));
          values.put(Song._ImageUri, song.getImageUri().getBytes("UTF-8"));
          values.put(Song._SongTitle, song.getSongTitle().getBytes("UTF-8"));
          values.put(Song._TrackId, song.getTrackId().getBytes("UTF-8"));
          values.put(Song._Uri, song.getUri().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
          LOGGER.log(Level.WARNING, "Could not save Song fully!", e);
        }

        ContentValues valuesCon = new ContentValues();

        try {
          valuesCon.put(IsSimilarTo._IsId, song.getTrackId().getBytes("UTF-8"));
          valuesCon.put(IsSimilarTo._ToId, toIT.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
          LOGGER.log(Level.WARNING, "Could not save SimilarTo fully!", e);
        }

        synchronized (db) {
          db.insert(Song._TabellenName, null, values);
          db.insert(IsSimilarTo._TabellenName, null, valuesCon);
        }

      }


      return result.isEmpty() ? false : true;

    } else {
      LOGGER.log(Level.WARNING, "Didn't find the song!");
      return false;
    }



  }

  @Override
  protected void postResult(Boolean result) {
    if (result) {
      ((ResultSimilarSongsActivity) activity).updateList();
    }
  }

}

