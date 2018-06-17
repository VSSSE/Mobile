package org.dhbw.movietunes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dhbw.movietunes.model.IsPlayedIn;
import org.dhbw.movietunes.model.IsSimilarTo;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.Song;

public class Database extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "MovieTunes.db";
  //SQL Primitives
  private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
  private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
  private static final String AS_INDEX = " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
  private static final String AS_BLOB = " BLOB NOT NULL";
  private static final String AS_BLOB_NL = " BLOB";

  //Sql umm Tabelle Movie zu erstellen
  private static final String CREATE_MOVIE_TABELLE =
          CREATE_TABLE + Movie._TabellenName + " ( "
                  + Movie._ID + AS_INDEX + ","
                  + Movie._MovieTitle + AS_BLOB_NL + ","
                  + Movie._MovieUri + AS_BLOB + ")";
  //Sql umm Tabelle Song zu erstellen
  private static final String CREATE_SONG_TABELLE =
          CREATE_TABLE + Song._TabellenName + " ( "
                  + Song._ID + AS_INDEX + ","
                  + Song._SongTitle + AS_BLOB_NL + ","
                  + Song._Artist + AS_BLOB + ","
                  + Song._Duration + AS_BLOB + ","
                  + Song._ImageUri + AS_BLOB + ","
                  + Song._TrackId + AS_BLOB + ","
                  + Song._Uri + AS_BLOB + ")";
  //Sql umm Tabelle IsPlayedIn zu erstellen
  private static final String CREATE_ISPLAYEDIN_TABELLE =
          CREATE_TABLE + IsPlayedIn._TabellenName + " ( "
                  + IsPlayedIn._ID + AS_INDEX + ","
                  + IsPlayedIn._SongId + AS_BLOB_NL + ","
                  + IsPlayedIn._MovieName + AS_BLOB_NL + ")";
  //Sql umm Tabelle IsSimilarTo zu erstellen
  private static final String CREATE_ISSIMILARTO_TABELLE =
          CREATE_TABLE + IsSimilarTo._TabellenName + " ( "
                  + IsSimilarTo._ID + AS_INDEX + ","
                  + IsSimilarTo._IsId + AS_BLOB_NL + ","
                  + IsSimilarTo._ToId + AS_BLOB_NL + ")";
  private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
  //SQL um Tabellen zu löschen
  private static final String SQL_DELETE_MOVIE = DROP_TABLE + Movie._TabellenName;
  private static final String SQL_DELETE_SONG = DROP_TABLE + Song._TabellenName;
  private static final String SQL_DELETE_ISPLAYEDIN = DROP_TABLE + IsPlayedIn._TabellenName;
  private static final String SQL_DELETE_ISSIMILARTO = DROP_TABLE + IsSimilarTo._TabellenName;

  private static SQLiteDatabase db;

  public Database(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  //Globale Datenbankverbindung
  public static SQLiteDatabase getDB(Context context) {
    if (db == null) {
      LOGGER.log(Level.INFO, "Save: Database war geschlossen");
      db = new Database(context).getWritableDatabase();
    }
    return db;
  }

  //Tabellen erstellen
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_MOVIE_TABELLE);
    db.execSQL(CREATE_SONG_TABELLE);
    db.execSQL(CREATE_ISPLAYEDIN_TABELLE);
    db.execSQL(CREATE_ISSIMILARTO_TABELLE);
  }

  //Tabellen updaten
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //TODO nicht löschen ... sondern updaten
    db.execSQL(SQL_DELETE_SONG);
    db.execSQL(SQL_DELETE_MOVIE);
    db.execSQL(SQL_DELETE_ISPLAYEDIN);
    db.execSQL(SQL_DELETE_ISSIMILARTO);
    onCreate(db);
  }
}
