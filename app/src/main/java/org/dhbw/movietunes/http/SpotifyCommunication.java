package org.dhbw.movietunes.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.dhbw.movietunes.exception.HttpException;
import org.dhbw.movietunes.extract.Extractor;
import org.dhbw.movietunes.model.PlaylistKey;
import org.dhbw.movietunes.model.Song;

public class SpotifyCommunication {

  private static final String HEADER_AUTHORIZATION = "Basic M2ZkOWFjYmJjZmY4NDVlNThhZTAxOGUwYTYwZjliMzU6ZTllODJjNmZjMDI0NGFiYWI1NWRiMTRjNGEzNjFhNzc=";
  private static final String HEADER_1 = "Authorization";
  private static final String HEADER_2 = "Bearer ";

  private String token;
  private Extractor extractor;

  public SpotifyCommunication() {
    token = fetchToken();
    extractor = new Extractor();
  }

  private String fetchToken() {
    RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials").build();

    Request request = new Request.Builder()
            .url("https://accounts.spotify.com/api/token")
            .addHeader(HEADER_1, HEADER_AUTHORIZATION)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .post(formBody)
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Empty Response! Could not get a Token!");
    }

    JsonElement root = new JsonParser().parse(responseBody);
    return root.getAsJsonObject().get("access_token").getAsString();
  }

  private String getJsonForSearchPlaylist(String searchString) {
    Request request = new Request.Builder()
            .url("https://api.spotify.com/v1/search?q=" + searchString + "&type=playlist")
            .addHeader(HEADER_1, HEADER_2 + token)
            .get()
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Response body for search is empty!");
    }
    return responseBody;
  }

  private String getSoundtrackJson(PlaylistKey playlistKey) {
    String user = playlistKey.getUserId();
    String playlist = playlistKey.getPlaylistId();
    String url = "https://api.spotify.com/v1/users/" + user + "/playlists/" + playlist + "/tracks?fields=items.track(id,name,duration_ms,artists, external_urls, album.images)";

    Request request = new Request.Builder()
            .url(url)
            .addHeader(HEADER_1, HEADER_2 + token)
            .get()
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Response body for Soundtrack Json is empty!");
    }
    return responseBody;
  }

  private String getRecommendationsBody(String trackId) {

    String url = "https://api.spotify.com/v1/recommendations?seed_tracks=" + trackId;

    Request request = new Request.Builder()
            .url(url)
            .addHeader(HEADER_1, HEADER_2 + token)
            .get()
            .build();

    String responseBody = HttpCommunication.executeRequest(request);
    if (responseBody == null || responseBody.isEmpty()) {
      throw new HttpException("Response body for Recommendations is empty!");
    }

    return responseBody;
  }

  public List<Song> findSoundtracks(String searchString) {

    PlaylistKey first = extractor.getFirstPlaylist(getJsonForSearchPlaylist(searchString));
    if (first == null) {
      return new ArrayList<Song>();
    } else {
      return extractor.getSongsFromPlaylist(getSoundtrackJson(first));
    }
  }

  public List<Song> getRecommendations(String trackId) {
    return extractor.getRecommendedSongs(getRecommendationsBody(trackId));
  }


}

