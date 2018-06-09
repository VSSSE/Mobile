package org.dhbw.se.movietunes.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.*;
import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.model.PlaylistKey;
import org.dhbw.se.movietunes.model.Song;

public class SpotifyCommunication {

  private static final Logger LOGGER = Logger.getLogger(SpotifyCommunication.class.getName());
  private static final String HEADER_AUTHORIZATION = "Basic M2ZkOWFjYmJjZmY4NDVlNThhZTAxOGUwYTYwZjliMzU6ZTllODJjNmZjMDI0NGFiYWI1NWRiMTRjNGEzNjFhNzc=";

  private String token;
  private Extractor extractor;

  public SpotifyCommunication() {
    token = fetchToken();
    extractor = new Extractor();
  }

  public String fetchToken() {
    RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials").build();

    Request request = new Request.Builder()
            .url("https://accounts.spotify.com/api/token")
            .addHeader("Authorization", HEADER_AUTHORIZATION)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .post(formBody)
            .build();

    String responseBody;

    Response response = HttpCommunication.executeRequest(request);
    try {
      responseBody = response.body().string();
    } catch (Exception e) {
      throw new HttpException("Empty Response! Could not get a Token!", e);
    }

    JsonElement root = new JsonParser().parse(responseBody);
    return root.getAsJsonObject().get("access_token").getAsString();
  }

  private String getResponseBodyForSearch(String searchString) {
    Request request = new Request.Builder()
            .url("https://api.spotify.com/v1/search?q=" + searchString + "&type=playlist")
            .addHeader("Authorization", "Bearer " + token)
            .get()
            .build();

    String responseBody;

    Response response = HttpCommunication.executeRequest(request);
    try {
      responseBody = response.body().string();
    } catch (Exception e) {
      throw new HttpException("Response body for search is empty!", e);
    }
    return responseBody;
  }

  public PlaylistKey findPlaylist(String searchString) {
    String responseBody = getResponseBodyForSearch(searchString);
    String playlistId = extractor.extractPlaylistIdFromSearchResult(responseBody);
    String userId = extractor.extractUserIdFromSearchResult(responseBody);
    String spotifyUrl = extractor.extractSpotifyUrl(responseBody);

    return new PlaylistKey(userId, playlistId, spotifyUrl);
  }

  public List<Song> getRecommendations(String trackId) {
    String response = getRecommendationsBody(trackId);
    return extractor.extractSongsFromRecommendationsResponse(response);
  }

  private String getRecommendationsBody(String trackId) {

    String url = "https://api.spotify.com/v1/recommendations?seed_tracks=" + trackId;

    Request request = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer " + token)
            .get()
            .build();

    String responseBody;

    Response response = HttpCommunication.executeRequest(request);
    try {
      responseBody = response.body().string();
    } catch (Exception e) {
      throw new HttpException("Response body for Recommendations is empty!", e);
    }

    return responseBody;
  }

  private String getSoundtrackJson(PlaylistKey playlistKey) {
    String user = playlistKey.getUserId();
    String playlist = playlistKey.getPlaylistId();
    String url = "https://api.spotify.com/v1/users/" + user + "/playlists/" + playlist + "/tracks?fields=items.track(id,name,duration_ms,artists, uri)";

    Request request = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer " + token)
            .get()
            .build();

    String responseBody;

    Response response = HttpCommunication.executeRequest(request);
    try {
      responseBody = response.body().string();
    } catch (Exception e) {
      throw new HttpException("Response body for Soundtrack Json is empty!", e);
    }
    return responseBody;
  }

  public List<Song> getSongsFromPlaylist(PlaylistKey playlistKey) {
    String body = getSoundtrackJson(playlistKey);
    return extractor.extractSongsFromTracklistDetails(body);
  }
}

