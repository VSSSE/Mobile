package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    ArrayList<Song> result;

    try {
      result = new ArrayList(spotifyCommunication.getRecommendations(searchString));
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, "Something bad happend", e);
      return false;
    }
    SQLiteDatabase db = Database.getDB(activity);

    String[] args = new String[]{searchString};
    Cursor finder = db.rawQuery("Select " + Song._TrackId
                    + " FROM " + Song._TabellenName + " as S"
                    + " WHERE S." + Song._TrackId + " = ?"
            , args);

    String isId;
    if (finder.getCount() > 0) {
      finder.moveToFirst();
      isId = finder.getString(finder.getColumnIndexOrThrow(Song._TrackId));
      finder.close();
    } else {
      //TODO search song if not exist and create
      LOGGER.log(Level.WARNING, "Could not find the song in DB!");

      return false;
    }


    for (Song song : result) {
      String toId;

      String[] argsTo = new String[]{song.getSongTitle()};
      Cursor finderTo = db.rawQuery("Select " + Song._TrackId
                      + " FROM " + Song._TabellenName + " as S"
                      + " WHERE S." + Song._SongTitle + " = ?"
              , argsTo);

      if (finderTo.getCount() > 0) {
        finderTo.moveToFirst();
        toId = finderTo.getString(finderTo.getColumnIndexOrThrow(Song._TrackId));
        finderTo.close();
      } else {
        ContentValues values = new ContentValues();

        values.put(Song._Artist, song.getArtist());
        values.put(Song._Duration, song.getDuration());
        values.put(Song._ImageUri, song.getImageUri());
        values.put(Song._SongTitle, song.getSongTitle());
        values.put(Song._TrackId, song.getTrackId());
        values.put(Song._Uri, song.getUri());
        synchronized (db) {
          db.insert(Song._TabellenName, null, values);
        }
        toId = song.getTrackId();
      }


      String[] argsCon = new String[]{toId, isId};
      Cursor finderCon = db.rawQuery("Select " + IsSimilarTo._ID
                      + " FROM " + IsSimilarTo._TabellenName + " as I"
                      + " WHERE I." + IsSimilarTo._ToId + " = ?"
                      + " AND I." + IsSimilarTo._IsId + " = ?"
              , argsCon);
      if (finderCon.getCount() == 0) {
        ContentValues valuesCon = new ContentValues();

        valuesCon.put(IsSimilarTo._ToId, toId);
        valuesCon.put(IsSimilarTo._IsId, isId);
        synchronized (db) {
          db.insert(IsSimilarTo._TabellenName, null, valuesCon);
        }
      }
      finderCon.close();
    }

    return result.isEmpty() ? false : true;

  }

  @Override
  protected void postResult(Boolean result) {
    if (result) {
      ((ResultSimilarSongsActivity) activity).updateList();
    }
  }

}

