package com.omar_hidrogo_local.mascotas.restApi.model;

import com.omar_hidrogo_local.mascotas.pojo.Relationship;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 23/08/2017.
 */

public class RelationshipResponse {

    //Se declara arreglo de Relationship
    ArrayList<Relationship> relationships;

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }
}
