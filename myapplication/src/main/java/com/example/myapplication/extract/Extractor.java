package com.example.myapplication.extract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Extractor {

    private JsonElement getFirstPlaylistElement (String playlistSearchResult){
        JsonElement root = new JsonParser().parse(playlistSearchResult);
        JsonObject playlists = root.getAsJsonObject().getAsJsonObject("playlists");

        JsonArray items = playlists.getAsJsonArray("items");
        if (items.size()<0){
            throw new RuntimeException("no playlists found");
        }

        JsonElement firstPlaylist = items.get(0);

        return firstPlaylist;
    }



    public String extractPlaylistIdFromSearchResult(String searchResult){

        JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);


        String id = firstPlaylist.getAsJsonObject().getAsJsonPrimitive("id").getAsString();

        return id;

    }



    public String extractUserIdFromSearchResult (String searchResult){
        JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);
        String userID = firstPlaylist.getAsJsonObject().getAsJsonObject("owner").getAsJsonPrimitive("id").getAsString();
        return userID;
    }

}
