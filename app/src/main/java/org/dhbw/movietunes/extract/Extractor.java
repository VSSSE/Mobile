package org.dhbw.movietunes.extract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.exception.ExtractorException;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;

public class Extractor {

  private PlaylistKey extractSinglePlaylist(JsonObject playlist) {
    return new PlaylistKey(
            playlist.getAsJsonObject("owner").getAsJsonPrimitive("id").getAsString(),
            playlist.getAsJsonPrimitive("id").getAsString(),
            playlist.getAsJsonPrimitive("name").getAsString(),
            playlist.getAsJsonObject("external_urls").getAsJsonPrimitive("spotify").getAsString());
  }

  private List<PlaylistKey> getListOfPlaylists(String playlistSearchResult) {
    JsonElement root = new JsonParser().parse(playlistSearchResult);
    JsonArray playlists = root.getAsJsonObject()
            .getAsJsonObject("playlists").getAsJsonArray("items");

    ArrayList<PlaylistKey> list = new ArrayList<>();

    for (JsonElement playlist : playlists) {
      list.add(extractSinglePlaylist(playlist.getAsJsonObject()));
    }

    return list;
  }

  public PlaylistKey getFirstPlaylist(String playlistSearchResult) {
    List<PlaylistKey> result = getListOfPlaylists(playlistSearchResult);

    if (result.isEmpty()) {
      return null;
    } else {
      return getListOfPlaylists(playlistSearchResult).get(0);
    }
  }

  private String extractArtists(JsonObject track) {
    String result = "";
    JsonArray artists = track.getAsJsonArray("artists");

    for (JsonElement artist : artists) {
      if (!result.isEmpty()) {
        result += ", ";
      }

      result += artist.getAsJsonObject().getAsJsonPrimitive("name").getAsString();
    }

    return result;
  }

  private String extractBestImage(JsonObject track) {
    JsonArray images = track.getAsJsonObject("album").getAsJsonArray("images");

    if (images.size() == 0) {
      return "";
    } else {
      return images.get(0).getAsJsonObject().getAsJsonPrimitive("url").getAsString();
    }

  }

  private Song extractSingleSong(JsonObject track) {

    return new Song(
            track.getAsJsonPrimitive("id").getAsString(),
            track.getAsJsonPrimitive("name").getAsString(),
            extractArtists(track),
            convertToSeconds(track.getAsJsonPrimitive("duration_ms").getAsString()),
            track.getAsJsonObject("external_urls").getAsJsonPrimitive("spotify").getAsString(),
            extractBestImage(track)
    );
  }

  public List<Song> getSongsFromPlaylist(String tracklistDetailsResponse) {
    List<Song> result = new ArrayList<>();

    JsonElement root = new JsonParser().parse(tracklistDetailsResponse);

    JsonArray items = root.getAsJsonObject().getAsJsonArray("items");

    for (JsonElement item : items) {
      JsonObject track = item.getAsJsonObject().getAsJsonObject("track");
      result.add(extractSingleSong(track));
    }
    return result;
  }

  public List<Song> getRecommendedSongs(String recommendationsBody) {
    List<Song> result = new ArrayList<>();
    JsonElement root = new JsonParser().parse(recommendationsBody);

    JsonArray tracks = root.getAsJsonObject().getAsJsonArray("tracks");
    for (JsonElement track : tracks) {
      result.add(extractSingleSong(track.getAsJsonObject()));
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
