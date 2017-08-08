package com.omar_hidrogo_local.mascotas.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.omar_hidrogo_local.mascotas.pojo.Instagram;
import com.omar_hidrogo_local.mascotas.restApi.JsonKeys;
import com.omar_hidrogo_local.mascotas.restApi.model.InstagramResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by tmhidrooma on 07/08/2017.
 */

public class InstagramDeserializador implements JsonDeserializer<InstagramResponse> {
    @Override
    public InstagramResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        InstagramResponse instagramResponse = gson.fromJson(json, InstagramResponse.class);
        JsonArray instagramResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        instagramResponse.setInstagrams(deserializadorInstagramDeJson(instagramResponseData));
        return instagramResponse;
    }

    private ArrayList<Instagram> deserializadorInstagramDeJson(JsonArray instagramResponseData){
        ArrayList<Instagram> instagram = new ArrayList<>();
        for(int i = 0; i < instagramResponseData.size(); i++){
            JsonObject instagramResponseDataObject = instagramResponseData.get(i).getAsJsonObject();

            JsonObject InstagramJson = instagramResponseDataObject.getAsJsonObject();

            String id = InstagramJson.get("id").getAsString();
            String username = InstagramJson.get("username").getAsString();
            String profile_picture = InstagramJson.get("profile_picture").getAsString();
            String full_name = InstagramJson.get("full_name").getAsString();
            String bio = InstagramJson.get("bio").getAsString();
            String website = InstagramJson.get("website").getAsString();

            Instagram instagramActual = new Instagram();
            instagramActual.setId(id);
            instagramActual.setUsername(username);
            instagramActual.setProfile_picture(profile_picture);
            instagramActual.setFull_name(full_name);
            instagramActual.setBio(bio);
            instagramActual.setWebsite(website);
            instagram.add(instagramActual);
        }
        return instagram;
    }
}
