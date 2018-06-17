package org.dhbw.movietunes.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import org.dhbw.movietunes.ResultMovieTitleActivity;
import org.dhbw.movietunes.database.Database;
import org.dhbw.movietunes.http.MovieCommunication;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchMovieTitleController extends AsyncSearchController {

  public SearchMovieTitleController(ResultMovieTitleActivity activity) {
    super(activity);
  }

  @Override
  protected Boolean search(String searchString) {
    MovieCommunication movieCommunication = new MovieCommunication();

    ArrayList<Movie> result = new ArrayList(movieCommunication.findMovies(searchString));

    SQLiteDatabase db = Database.getDB(activity);

    String[] args = new String[]{searchString};
    Cursor finder = db.rawQuery("Select " + Song._SongTitle
                    + " FROM " + Song._TabellenName + " as S"
                    + " WHERE S." + Song._SongTitle + " = ?"
            , args);

    String songName;
    if (finder.getCount() > 0) {
      finder.moveToFirst();
      songName = finder.getString(finder.getColumnIndexOrThrow(Song._SongTitle));
    } else {
      //TODO search song if not exist

      songName = searchString;

      ContentValues valuesCreate = new ContentValues();

      valuesCreate.put(Song._SongTitle, songName);

      synchronized (db) {
        db.insert(Song._TabellenName, null, valuesCreate);
      }
    }

    for (Movie movie : result) {
      ContentValues values = new ContentValues();

      values.put(Movie._MovieTitle, movie.getMovieTitle());
      values.put(Movie._MovieUri, movie.getMovieUri());

      ContentValues valuesCon = new ContentValues();

      valuesCon.put(IsPlayedIn._MovieName, movie.getMovieTitle());
      valuesCon.put(IsPlayedIn._SongName, songName);

      synchronized (db) {
        db.insert(Movie._TabellenName, null, values);
        db.insert(IsPlayedIn._TabellenName, null, valuesCon);
      }

    }

    return result.isEmpty() ? false : true;

  }

  @Override
  protected void postResult(Boolean result) {
    if (result) {
      ((ResultMovieTitleActivity) activity).updateList();
    }
  }

}

