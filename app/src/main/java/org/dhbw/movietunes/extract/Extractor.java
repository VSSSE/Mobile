package org.dhbw.movietunes.extract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dhbw.movietunes.http.HttpCommunication;
import org.dhbw.movietunes.model.Movie;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;
import org.dhbw.movietunes.model.Video;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Extractor {

  private static final Logger LOGGER = Logger.getLogger(Extractor.class.getName());

  private static final String parseError = "It was not possible to parse the result!";

  private PlaylistKey extractSinglePlaylist(JsonObject playlist) {
    try {
     return new PlaylistKey(
            playlist.getAsJsonObject("owner").getAsJsonPrimitive("id").getAsString(),
            playlist.getAsJsonPrimitive("id").getAsString(),
            playlist.getAsJsonPrimitive("name").getAsString(),
            playlist.getAsJsonObject("external_urls").getAsJsonPrimitive("spotify").getAsString());

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
      return  null;
    }
  }

  private List<PlaylistKey> getListOfPlaylists(String playlistSearchResult) {

    ArrayList<PlaylistKey> list = new ArrayList<>();
    try {
      JsonElement root = new JsonParser().parse(playlistSearchResult);
      JsonArray playlists = root.getAsJsonObject()
              .getAsJsonObject("playlists").getAsJsonArray("items");

      int counter = 0;
      for (JsonElement playlist : playlists) {
          PlaylistKey newPlaylist = extractSinglePlaylist(playlist.getAsJsonObject());
        if (newPlaylist != null) {
            list.add(newPlaylist);
            if(++counter > 100) {
                break;
            }
        }
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return list;
  }
  public PlaylistKey getFirstPlaylist(String playlistSearchResult) {
    List<PlaylistKey> result = getListOfPlaylists(playlistSearchResult);

    if (result.isEmpty()) {
      return null;
    } else {
      return result.get(0);
    }
  }
  private List<Song> getListOfSongs(String songSearchResult) {
    List<Song> result = new ArrayList<>();

    try {
      JsonElement root = new JsonParser().parse(songSearchResult);

      JsonArray items = root.getAsJsonObject().getAsJsonObject("tracks").getAsJsonArray("items");

      int counter = 0;
      for (JsonElement item : items) {
        JsonObject track = item.getAsJsonObject();
        Song newSong = extractSingleSong(track);
        if (newSong != null) {
          result.add(newSong);
            if(++counter > 100) {
                break;
            }
        }

      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return result;
  }

  public Song getFirstSong(String songSearchResult) {
    List<Song> result = getListOfSongs(songSearchResult);

    if (result.isEmpty()) {
      return null;
    } else {
      return result.get(0);
    }
  }

  private String extractArtists(JsonObject track) {
    String result = "";
    try {
      JsonArray artists = track.getAsJsonArray("artists");

      for (JsonElement artist : artists) {
        if (!result.isEmpty()) {
          result += ", ";
        }

        result += artist.getAsJsonObject().getAsJsonPrimitive("name").getAsString();
      }


    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return result;
  }

  private String extractBestImage(JsonObject track) {
    try {
    JsonArray images = track.getAsJsonObject("album").getAsJsonArray("images");

      if (images.size() == 0) {
        return "";
      } else {
        return images.get(0).getAsJsonObject().getAsJsonPrimitive("url").getAsString();
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
      return "";
    }

  }

  private Song extractSingleSong(JsonObject track) {
    try {
      return new Song(
              track.getAsJsonPrimitive("id").getAsString(),
              track.getAsJsonPrimitive("name").getAsString(),
              extractArtists(track),
              convertToSeconds(track.getAsJsonPrimitive("duration_ms").getAsString()),
              track.getAsJsonObject("external_urls").getAsJsonPrimitive("spotify").getAsString(),
              extractBestImage(track)
      );
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
      return null;
    }

  }

  private Video extractSingleVideo(JsonObject video) {
    try {
      return new Video(
              video.getAsJsonObject("id").getAsJsonPrimitive("videoId").getAsString(),
              video.getAsJsonObject("snippet").getAsJsonPrimitive("title").getAsString()
      );
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
      return null;
    }

  }

  public List<Song> getSongsFromPlaylist(String tracklistDetailsResponse) {
    List<Song> result = new ArrayList<>();

    try {
      JsonElement root = new JsonParser().parse(tracklistDetailsResponse);

      JsonArray items = root.getAsJsonObject().getAsJsonArray("items");
      int counter = 0;
      for (JsonElement item : items) {
        JsonObject track = item.getAsJsonObject().getAsJsonObject("track");
        Song newSong = extractSingleSong(track);
        if (newSong != null) {
          result.add(newSong);
            if(++counter > 100) {
                break;
            }
        }
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return result;
  }

  public List<Song> getRecommendedSongs(String recommendationsBody) {
    List<Song> result = new ArrayList<>();

    try {
      JsonElement root = new JsonParser().parse(recommendationsBody);

      JsonArray tracks = root.getAsJsonObject().getAsJsonArray("tracks");
      int counter = 0;
      for (JsonElement track : tracks) {
        Song newSong = extractSingleSong(track.getAsJsonObject());
        if (newSong != null) {
          result.add(newSong);
            if(++counter > 100) {
                break;
            }
        }
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return result;
  }

  private List<Video> getVideos(String recommendationsBody) {
    List<Video> result = new ArrayList<>();

    try {
      JsonElement root = new JsonParser().parse(recommendationsBody);

      JsonArray videos = root.getAsJsonObject().getAsJsonArray("items");
      int counter = 0;
      for (JsonElement video : videos) {
        Video newVideo = extractSingleVideo(video.getAsJsonObject());
        if (newVideo != null) {
          result.add(newVideo);
            if(++counter > 100) {
                break;
            }
        }
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }
    return result;
  }

  public Video getFirstVideo(String searchString) {
    List<Video> found = getVideos(searchString);
    if (found.isEmpty()) {
      return null;
    } else {
      return found.get(0);
    }
  }

  private Movie extractSingleMovie(Element eintrag) {
    try {

      Elements links = eintrag.select("a");
      if (links.size() >= 2) {
        return new Movie(
                links.get(1).attr("href"),
                links.get(1).text()
        );
      } else {
        return null;
      }

    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
      return null;
    }

  }

  public List<Movie> getMovies(String recommendationsBody) {
    List<Movie> result = new ArrayList<>();

    try {
      Document document = Jsoup.parse(recommendationsBody);
      Elements elements = document.select("OL");
      Elements eintraege = elements.first().select("LI");

      int counter = 0;
      for (Element eintrag : eintraege) {
        Movie newmovie = extractSingleMovie(eintrag);
        if (newmovie != null) {
          result.add(newmovie);
            if(++counter > 100) {
                break;
            }
        }
      }
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, parseError, e);
    }

    return result;
  }

  private String convertToSeconds(String s) {
    int ml = Integer.parseInt(s);
    ml = ml / 1000;
    int min = ml / 60;
    int sec = ml % 60;

    return min + ":" + sec;
  }

}
