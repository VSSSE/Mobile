package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import org.dhbw.movietunes.ResultMovieSoundtracksActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.SpotifyCommunication;
import org.dhbw.movietunes.model.IsPlayedIn;
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
      movieName = finder.getString(finder.getColumnIndexOrThrow(Movie._MovieTitle));
      finder.close();
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
      String songName;

      String[] argsTo = new String[]{song.getSongTitle()};
      Cursor finderTo = db.rawQuery("Select " + Song._SongTitle
                      + " FROM " + Song._TabellenName + " as S"
                      + " WHERE S." + Song._SongTitle + " = ?"
              , argsTo);

      if (finderTo.getCount() > 0) {
        finderTo.moveToFirst();
        songName = finderTo.getString(finderTo.getColumnIndexOrThrow(Song._SongTitle));
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
        songName = song.getSongTitle();

      }

      String[] argsCon = new String[]{movieName, songName};
      Cursor finderCon = db.rawQuery("Select " + IsPlayedIn._ID
                      + " FROM " + IsPlayedIn._TabellenName + " as I"
                      + " WHERE I." + IsPlayedIn._MovieName + " = ?"
                      + " AND I." + IsPlayedIn._SongName + " = ?"
              , argsCon);
      if (finderCon.getCount() == 0) {
        ContentValues valuesCon = new ContentValues();

        valuesCon.put(IsPlayedIn._SongName, songName);
        valuesCon.put(IsPlayedIn._MovieName, movieName);

        synchronized (db) {
          db.insert(IsPlayedIn._TabellenName, null, valuesCon);
        }

      }
      finderCon.close();

    }

    return result.isEmpty() ? false : true;
  }

  @Override
  protected void postResult(Boolean result) {
    if (result) {
      ((ResultMovieSoundtracksActivity) activity).updateList();
    }
  }

}

