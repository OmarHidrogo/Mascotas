package com.omar_hidrogo_local.mascotas.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.omar_hidrogo_local.mascotas.pojo.Relationship;
import com.omar_hidrogo_local.mascotas.restApi.JsonKeys;
import com.omar_hidrogo_local.mascotas.restApi.model.RelationshipResponse;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by tmhidrooma on 23/08/2017.
 */

public class RelationshipDeserializador implements JsonDeserializer<RelationshipResponse> {
    @Override
    public RelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //return null;

        Gson gson = new Gson();
        RelationshipResponse relationshipResponse = gson.fromJson(json, RelationshipResponse.class);
        JsonArray relationshipResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        relationshipResponse.setRelationships(deserializarRelationshipDeJson(relationshipResponseData));
        return relationshipResponse;
    }

    private ArrayList<Relationship> deserializarRelationshipDeJson(JsonArray relationshipResponseData){

        ArrayList<Relationship> relationships = new ArrayList<>();

        for (int i = 0; i < relationshipResponseData.size(); i++) {
            JsonObject relationshipResponseDataObject = relationshipResponseData.get(i).getAsJsonObject();

            JsonObject RelationshipJson = relationshipResponseDataObject.getAsJsonObject();

            String outgoing_status = RelationshipJson.get("outgoing_status").getAsString();
            String incoming_status = RelationshipJson.get("incoming_status").getAsString();

            /*String outgoing_status = relationshipResponseDataObject.get(JsonKeys.RELATIONSHIP_OUT).getAsString();
            String incoming_status = relationshipResponseDataObject.get(JsonKeys.RELATIONSHIP_IN).getAsString();*/

            Relationship relationshipActual = new Relationship();
            relationshipActual.setOutgoing_status(outgoing_status);
            relationshipActual.setIncoming_status(incoming_status);

            relationships.add(relationshipActual);

        }
        return relationships;
    }

}
