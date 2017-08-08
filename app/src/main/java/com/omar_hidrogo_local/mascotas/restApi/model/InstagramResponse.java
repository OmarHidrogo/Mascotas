package com.omar_hidrogo_local.mascotas.restApi.model;

import com.omar_hidrogo_local.mascotas.pojo.Instagram;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 07/08/2017.
 */

public class InstagramResponse {
    ArrayList<Instagram> instagrams;

    public ArrayList<Instagram> getInstagrams(){
        return instagrams;
    }

    public void  setInstagrams(ArrayList<Instagram> instagrams){
        this.instagrams = instagrams;
    }
}
